package com.example.demo.covid.services;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.covid.dtos.CovidLocation;
import com.example.demo.covid.dtos.GoogleLocation;
import com.example.demo.covid.dtos.SuspectionDto;
import com.example.demo.covid.dtos.SuspectionReport;
import com.example.demo.covid.dtos.TimelineObject;
import com.example.demo.covid.exception.FileParseException;
import com.example.demo.covid.repositiories.InfectedAreasRepository;
import com.example.demo.covid.repositiories.SuspectionReportRepository;
import com.example.demo.covid.util.ApiUtility;
import com.example.demo.covid.util.EmailUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author explorervarun
 *
 */
@Service
public class CandidateService {
	
	@Autowired
	ApiUtility apiUtil;

	@Autowired
	private InfectedAreasRepository infectedAreasRep;
	
	@Autowired
	private SuspectionReportRepository suspectionReport;
	
	private static final Logger LOG = LoggerFactory.getLogger(CandidateService.class);	
	
	public boolean postInfectedAreasFile(MultipartFile[] files) throws ParseException, FileParseException {

		List<CovidLocation> updated ;
		ArrayList<GoogleLocation> monthlyData = new ArrayList<GoogleLocation>();
		ArrayList<CovidLocation> covidLocations = new ArrayList<CovidLocation>();
		ObjectMapper mapper = new ObjectMapper();
		for (MultipartFile file : files) {
			try {
				byte[] bytes = file.getBytes();
				String completeData = new String(bytes);
				GoogleLocation location = mapper.readValue(completeData.getBytes(), GoogleLocation.class);
				
				
				for(TimelineObject timeLine :location.getTimelineObjects()) {
					CovidLocation covidLoc = new CovidLocation();
					if(timeLine.getPlaceVisit() != null && timeLine.getPlaceVisit().getLocation() != null 
							&& timeLine.getPlaceVisit().getDuration() != null) {
					if(!StringUtils.isEmpty(timeLine.getPlaceVisit().getLocation().getLatitudeE7())){
						String latitude = timeLine.getPlaceVisit().getLocation().getLatitudeE7().toString();
						covidLoc.setLatitude(latitude.substring(0, 2) + "." + latitude.substring(2));
					};
					
					if(!StringUtils.isEmpty(timeLine.getPlaceVisit().getLocation().getLongitudeE7())){
						String longitude = timeLine.getPlaceVisit().getLocation().getLongitudeE7().toString();
						covidLoc.setLongitude(longitude.substring(0, 2) + "." + longitude.substring(2));
					};
					
					if(!StringUtils.isEmpty(timeLine.getPlaceVisit().getLocation().getAddress())){
						covidLoc.setAddressName(timeLine.getPlaceVisit().getLocation().getAddress());
					} else {
						covidLoc.setAddressName("Unrecognized location");
					};
					
					if(!StringUtils.isEmpty(timeLine.getPlaceVisit().getLocation().getName())){
						covidLoc.setName(timeLine.getPlaceVisit().getLocation().getName());
					} else {
						covidLoc.setName("Unrecognized location");
					};
					
					if(!StringUtils.isEmpty(timeLine.getPlaceVisit().getDuration().getStartTimestampMs())) {
						Calendar calendar = Calendar.getInstance();
						SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
						long millis = Long.parseLong(timeLine.getPlaceVisit().getDuration().getStartTimestampMs());
				    	calendar.setTimeInMillis(millis);
				    	covidLoc.setDateField(sdf.format(calendar.getTime()));
				    	
					}
					}
					covidLoc.setId(UUID.randomUUID().toString());
					covidLocations.add(covidLoc);
					
				}
				monthlyData.add(location);
				updated = infectedAreasRep.saveAll(covidLocations);
			} catch (IOException e) {
				throw new FileParseException(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
			return true;
	}
	
	public boolean postInfectedAreas(List<CovidLocation> locations) {
		List<CovidLocation> updated =infectedAreasRep.saveAll(locations);
		
		if(updated.size() == locations.size()) {
			return true;
		}else {
			return false;
		}
	}

	public ResponseEntity<List<CovidLocation>> getAllInfectedAreas() {
		return new ResponseEntity<List<CovidLocation>>(infectedAreasRep.findAll(),HttpStatus.OK);
	}

	public boolean postSuspectionDetails(SuspectionDto report) throws Exception {
		SuspectionReport susRep = new SuspectionReport();
		susRep.setAadharNo(report.getAadharNo());
		susRep.setAddressLine1(report.getAddress().getLine1());
		susRep.setAddressLine2(report.getAddress().getLine2());
		susRep.setAddressLine3(report.getAddress().getLine3());
		susRep.setCity(report.getAddress().getCity());
		susRep.setCountry(report.getAddress().getCountry());
		susRep.setDateOfSusInf(report.getDateOfSusInf());
		susRep.setEmail(report.getEmail());
		susRep.setMobileNo(report.getMobileNo());
		susRep.setName(report.getName());
		susRep.setState(report.getAddress().getState());
		susRep.setSuspectedLocationId(report.getSuspectedLocationId());
		susRep.setPincode(report.getAddress().getZipcode());
		
		SuspectionReport uploaded = suspectionReport.save(susRep);
		Optional<CovidLocation> covLoc = infectedAreasRep.findById(susRep.getSuspectedLocationId());
		if(covLoc.isPresent()) {
			CovidLocation covLocation = covLoc.get();
			EmailUtil.sendEmail(susRep,covLocation.getName());
		}
		if(uploaded.getName() == report.getName()) {
			return true;
		}else {
			return false;
		}
	}
}

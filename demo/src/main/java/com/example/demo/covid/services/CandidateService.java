/*******************************************************************************
 * Copyright     : Copyright (c)  2020 Honeywell Inc. All rights reserved.
 *                 This work contains trade secrets and confidential materials
 *                 of Honeywell Inc., and its use or disclosure in whole or in 
 *                 part without express written permission of Honeywell Inc. 
 *                 is prohibited.
 *
 * Name          :  CandidateService.java.java
 * 
 * Project Title :  demo 
 *  
 * Description	:
 * 
 * 
 * Company       : Honeywell Connected Enterprise, Hyderabad
 * 
 * @Author       : H156833
 * 
 * @Date		 : Apr 4, 2020
 *
 * @Version      : Version 1.0
 * 
 * Modification History:
 * 
 * By            : <Modifier Name>
 * On            : <Modified Date>
 * Description   : <Description of modification>
 ******************************************************************************/
package com.example.demo.covid.services;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.covid.dtos.CovidLocation;
import com.example.demo.covid.dtos.GoogleLocation;
import com.example.demo.covid.dtos.TimelineObject;
import com.example.demo.covid.repositiories.InfectedAreasRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author H156833
 *
 */
@Service
public class CandidateService {

	@Autowired
	private InfectedAreasRepository infectedAreasRep;
	
	
	private static final Logger LOG = LoggerFactory.getLogger(CandidateService.class);	
	
	public boolean postInfectedAreasFile(MultipartFile[] files) throws ParseException {

		ArrayList<GoogleLocation> monthlyData = new ArrayList<GoogleLocation>();
		ArrayList<CovidLocation> covidLocations = new ArrayList<CovidLocation>();
		ObjectMapper mapper = new ObjectMapper();
		for (MultipartFile file : files) {
			try {
				byte[] bytes = file.getBytes();
				String completeData = new String(bytes);
				GoogleLocation location = mapper.readValue(completeData.getBytes(), GoogleLocation.class);
				CovidLocation covidLoc = new CovidLocation();
				
				for(TimelineObject timeLine :location.getTimelineObjects()) {
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
					covidLoc.setId(UUID.randomUUID().toString());
					covidLocations.add(covidLoc);
					
				}
				monthlyData.add(location);
				infectedAreasRep.saveAll(covidLocations);
			} catch (IOException e) {
				LOG.error(e.getMessage());
			}
		}
		
		return true;

	}

	public ResponseEntity<List<CovidLocation>> getAllInfectedAreas() {
		return new ResponseEntity<List<CovidLocation>>(infectedAreasRep.findAll(),HttpStatus.OK);
	}
}

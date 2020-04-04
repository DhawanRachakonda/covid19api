package com.example.demo.covid.controllers;

import java.text.ParseException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.covid.dtos.Candidate;
import com.example.demo.covid.dtos.CovidLocation;
import com.example.demo.covid.dtos.SuspectionDto;
import com.example.demo.covid.exception.FileParseException;
import com.example.demo.covid.repositiories.CandidateRepository;
import com.example.demo.covid.services.CandidateService;

@RestController
@RequestMapping("/api/v1/ic-tracker")
public class CandidateController {

	@Autowired
	private CandidateRepository candidateRepository;

	@Autowired
	private CandidateService candidateService;

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Candidate add(@RequestBody Candidate candidate) {
		return candidateRepository.save(candidate);
	}

	@GetMapping(value = "/infected-areas", produces = { "application/json" })
	public ResponseEntity<List<CovidLocation>> getInfectedAreas() {
		return candidateService.getAllInfectedAreas();
	}

	@PostMapping(value = "/infected-areas-file", produces = { "application/json" })
	@ResponseStatus(code = HttpStatus.CREATED)
	public boolean postInfectedAreasFile(@RequestParam("files") MultipartFile[] files) throws ParseException, FileParseException {
		return candidateService.postInfectedAreasFile(files);
	}

	@PostMapping(value = "/infected-areas", produces = { "application/json" })
	@ResponseStatus(code = HttpStatus.CREATED)
	public boolean postInfectedAreas(@Valid @RequestBody List<CovidLocation> locations) {
		return candidateService.postInfectedAreas(locations);
	}

	@PostMapping(value = "/suspection-details", produces = { "application/json" })
	@ResponseStatus(code = HttpStatus.CREATED)
	public boolean postSuspectDetails(@Valid @RequestBody SuspectionDto report) throws Exception {
		return candidateService.postSuspectionDetails(report);

	}

}
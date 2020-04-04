package com.example.demo.covid.controllers;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
import com.example.demo.covid.dtos.Location;
import com.example.demo.covid.dtos.SuspectionReport;
import com.example.demo.covid.dtos.User;
import com.example.demo.covid.repositiories.CandidateRepository;
import com.example.demo.covid.services.CandidateService;

@RestController
@RequestMapping("/api/v1/icTracker")
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
    
	
	/*
	 * @GetMapping(value = "/user", produces = { "application/json" }) public User
	 * getUser() {
	 * 
	 * }
	 * 
	 * @PostMapping(value = "/user", produces = { "application/json" }) public User
	 * createUser() {
	 * 
	 * }
	 */
	  
	  @GetMapping(value = "/infectedAreas", produces = { "application/json" })
	  public ResponseEntity<List<CovidLocation>> getInfectedAreas() {
		  	return candidateService.getAllInfectedAreas();
	  }
	 
    
    @PostMapping(value = "/infectedAreasFile", produces = { "application/json" })
    public boolean postInfectedAreasFile(@RequestParam("files") MultipartFile[] files) throws ParseException {
        return candidateService.postInfectedAreasFile(files);
    }
    
	/*
	 * @PostMapping(value = "/infectedAreas", produces = { "application/json" })
	 * public boolean postInfectedAreas(@Valid @RequestBody ArrayList<Location>
	 * locations) { return Arrays.asList(files) .stream() .map(file ->
	 * uploadFile(file)) .collect(Collectors.toList()); }
	 * 
	 * @PostMapping(value = "/suscpectionDetails", produces = { "application/json"
	 * }) public String postSuspectDetails(@Valid @RequestBody SuspectionReport
	 * report) {
	 * 
	 * }
	 */

}
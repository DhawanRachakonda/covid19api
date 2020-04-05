package com.example.demo.covid.repositiories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.covid.dtos.CovidLocation;

/**
 * @author explorervarun
 *
 */
public interface InfectedAreasRepository extends MongoRepository<CovidLocation,String>{

}

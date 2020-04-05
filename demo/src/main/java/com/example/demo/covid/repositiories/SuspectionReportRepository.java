package com.example.demo.covid.repositiories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.covid.dtos.SuspectionReport;

/**
 * @author explorervarun
 *
 */
public interface SuspectionReportRepository extends MongoRepository<SuspectionReport, String> {

}

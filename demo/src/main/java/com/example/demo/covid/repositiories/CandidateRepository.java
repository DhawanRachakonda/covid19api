package com.example.demo.covid.repositiories;

import com.example.demo.covid.dtos.Candidate;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface CandidateRepository extends MongoRepository<Candidate, String> {
}

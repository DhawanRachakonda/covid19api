package com.example.demo.covid.repositiories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.covid.dtos.AppUser;

/**
 * @author explorervarun
 *
 */
public interface UserRepository extends MongoRepository<AppUser, String> {

	AppUser findByUsername(String username);
}

package com.example.IndividualProject.repository;

import com.example.IndividualProject.model.Country;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface CountryRepository extends MongoRepository<Country, String> {

}

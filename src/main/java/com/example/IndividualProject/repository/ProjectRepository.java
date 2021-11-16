package com.example.IndividualProject.repository;

import com.example.IndividualProject.model.Project;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

@Service
public interface ProjectRepository extends MongoRepository<Project, String> {


}

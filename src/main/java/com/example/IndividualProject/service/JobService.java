package com.example.IndividualProject.service;

import com.example.IndividualProject.model.Job;
import com.example.IndividualProject.repository.JobRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class JobService {
    private final JobRepository jobRepository;

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    public Job getJobById(String id) {
        return jobRepository.findById(id).orElse(null);
    }

    public Job addJob(Job job) {
        return jobRepository.insert(job);
    }

    public Job updateJob(Job job) {
        return jobRepository.save(job);
    }

    public void deleteJobById(String id){
        jobRepository.deleteById(id);
    }
}

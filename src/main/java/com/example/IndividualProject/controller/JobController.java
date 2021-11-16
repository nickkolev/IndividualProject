package com.example.IndividualProject.controller;

import com.example.IndividualProject.model.*;
import com.example.IndividualProject.repository.FakeIData;

import com.example.IndividualProject.service.JobService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@CrossOrigin
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private final JobService jobService;

    @GetMapping("/hello")
    @ResponseBody
    public String SayHello() {
        return "Hello, your resources work!";
    }

    @GetMapping
    public ResponseEntity<List<Job>> getAllJobs() {

        List<Job> jobs = jobService.getAllJobs();

        if (jobs != null) {
            return ResponseEntity.ok().body(jobs);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("{id}")
    public ResponseEntity<Job> getJob(@PathVariable(value = "id") String id) {
        Job job = jobService.getJobById(id);

        if (job != null) {
            return ResponseEntity.ok().body(job);
        } else {
            return new ResponseEntity("Job with ID " + id + " does not exists.", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public ResponseEntity<Job> createJob(@RequestBody Job job) {
        Job created = jobService.addJob(job);

        if (created == null) {
            String entity = "Job with ID " + job.getId() + " already exists.";
            return new ResponseEntity(entity, HttpStatus.CONFLICT);
        }

        String url = "job" + "/" + created.getId();
        URI uri = URI.create(url);
        return new ResponseEntity(uri, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Job> updateJob(
            @PathVariable("id") String id, @RequestBody Job job) {
        Job old = jobService.getJobById(id);

        if (old == null) {
            return new ResponseEntity("Job not found.", HttpStatus.NOT_FOUND);
        }

        old.setJobName(job.getJobName());
        old.setCategory(job.getCategory());
        old.setEstimatedTime(job.getEstimatedTime());
        old.setPayRate(job.getPayRate());

        Job updated = jobService.updateJob(old);
        return ResponseEntity.ok().body(updated);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteJob(@PathVariable String id) {
        Job job = jobService.getJobById(id);

        if(job == null){
            return new ResponseEntity("Job not found.", HttpStatus.NOT_FOUND);
        }

        jobService.deleteJobById(id);
        return ResponseEntity.ok().build();
    }
}

package com.example.IndividualProject.model;

import com.example.IndividualProject.repository.FakeIData;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "Jobs")
public class Job {

    @Id
    private String id;
    private String jobName;
    private String category;
    private double payRate;
    private double estimatedTime;

    public Job(String jobName, String category, double payRate, double estimatedTime) {
        this.jobName = jobName;
        this.category = category;
        this.payRate = payRate;
        this.estimatedTime = estimatedTime;
    }

    public Job() {
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public double getPayRate() {
        return payRate;
    }

    public void setPayRate(double payRate) {
        this.payRate = payRate;
    }

    public double getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(double estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return String.format("Job: " + jobName + "\nCategory: " + category +
                "\nPay Rate: " + payRate + "\nEstimated Time: " + estimatedTime);
    }

    public double CalculateAveragePayRate() {
        FakeIData data = new FakeIData();
        List<Job> jobs = data.getJobsByCategory("IT");
        double average =
                jobs.stream()
                        .mapToDouble(j -> j.getPayRate())
                        .average()
                        .orElse(0);
        return average;
    }
}

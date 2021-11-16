package com.example.IndividualProject.model;

import com.example.IndividualProject.repository.FakeIData;
import com.example.IndividualProject.service.UserService;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "users")
public class User {

    @Id
    private String id;
    private String name;
    private String userType; //{normal, subscriber, admin}
    private Country country;
    private Job job;
    @Indexed(unique = true)
    private String email;
    private int age;

    public User(String name, String userType, Country country, String email, int age) {
        this.name = name;
        this.userType = userType;
        this.country = country;
        this.email = email;
        this.age = age;
    }

    public User(String name, String userType, Country country, Job job, String email, int age) {
        this.name = name;
        this.userType = userType;
        this.country = country;
        this.job = job;
        this.email = email;
        this.age = age;
    }

    public User() {
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return String.format("User: %s%n" +
                "email: %s%n" +
                "Age: %d%n" +
                "Country: %s%n", name, email, age, country);
    }

    public double CalculateAverageYears()
    {
        FakeIData data = new FakeIData();
        List<User> users = data.getUsers();
        double average =
                users.stream()
                        .mapToDouble(u -> u.getAge())
                        .average()
                        .orElse(0);
        return average;
    }
}

package com.example.IndividualProject.repository;

import com.example.IndividualProject.Interfaces.iData;
import com.example.IndividualProject.model.Country;
import com.example.IndividualProject.model.Job;
import com.example.IndividualProject.model.Project;
import com.example.IndividualProject.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;

@Service
public class FakeIData implements iData{

    @Override
    public void writeTextt() {
        System.out.println("Hello from FakeData");
    }

    private final List<User> userList = new ArrayList<>();
    private final List<Country> countryList = new ArrayList<>();
    private final List<Job> jobsList = new ArrayList<>();

    @Autowired
    public FakeIData() {
        Country netherlands = new Country("The Netherlands", "NL", true);
        Country bulgaria = new Country("Bulgaria", "BG", true);
        Country china = new Country("China", "CNH", false);
        Country germany = new Country("Germany", "GR", true);
        Country poland = new Country("Poland", "PL", true);
        Country australia = new Country("Australia", "AU", false);
        Country usa = new Country("America", "USA", false);

        countryList.add(netherlands);
        countryList.add(bulgaria);
        countryList.add(china);
        countryList.add(germany);
        countryList.add(poland);
        countryList.add(australia);
        countryList.add(usa);

        Job softwareEngineer = new Job("Software Engineer", "IT", 50, 20);
        Job wordPressExpert = new Job("Word Press expert", "IT", 35, 15);
        Job shopifyExpert = new Job("Shopify expert", "Marketing", 25, 20);
        Job googleAddsExpert = new Job("Google Adds expert", "Marketing", 27.5, 18);
        Job englishTeacher = new Job("English teacher", "Teaching", 18, 100);
        Job copywriter = new Job("Copywriter", "Copywriting", 25, 10);

        jobsList.add(softwareEngineer);
        jobsList.add(wordPressExpert);
        jobsList.add(shopifyExpert);
        jobsList.add(googleAddsExpert);
        jobsList.add(englishTeacher);
        jobsList.add(copywriter);

        userList.add(new User("Joe Kovacs", "Subscriber", usa, softwareEngineer, "mail@test.com",27));
        userList.add(new User("Test Worker", "Normal", germany, softwareEngineer, "mail@test.com", 28));
        userList.add(new User("Sean Donnoley", "Subscriber", usa, shopifyExpert, "mail@test.com", 30));
        userList.add(new User("Ann Johnsonn", "Subscriber", usa, shopifyExpert, "mail@test.com", 51));
        userList.add(new User("Shu Wang", "Normal", china, englishTeacher, "mail@test.com", 44));
        userList.add(new User("Albert Huffman", "Subscriber", germany, englishTeacher, "mail@test.com", 31));
        userList.add(new User("Khaang Wing", "Normal", china, copywriter, "mail@test.com", 21));
        userList.add(new User("Ashton John", "Normal", australia, copywriter, "mail@test.com", 18));
        userList.add(new User("Nikola Kolev", "Admin", bulgaria, copywriter, "mail@test.com", 20));

    }

    // GET all users
    public List<User> getUsers() {
        return userList;
    }

    // GET all users from a given country
    public List<User> getUsers(Country country) {
        List<User> filtered = new ArrayList<>();
        for (User user : userList) {
            if (user.getCountry().equals(country)) {
                filtered.add(user);
            }
        }
        return filtered;
    }

    // GET all users with a given userType
    public List<User> getUsers(String userType) {
        List<User> filtered = new ArrayList<>();
        for (User user : userList) {
            if (user.getUserType().equals(userType)) {
                filtered.add(user);
            }
        }
        return filtered;
    }

    // GET the user with the given ID
    public User getUser(String id) {
        for (User user : userList) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    // GET all the countries
    public List<Country> getAllCountries() {
        return countryList;
    }

    // DELETE a user with the given ID
    public void deleteUser(String id) {
        User user = getUser(id);
        if (user == null) {
            return;
        }
        userList.remove(user);
    }

    // CREATE a new user
    public boolean createUser(User user) {
        if (this.getUser(user.getId()) != null) {
            return false;
        }
        userList.add(user);
        return true;
    }

    // PUT - Update an existing user
    public boolean updateUser(User user) {
        User old = this.getUser(user.getId());
        if (old == null) {
            return false;
        }
        old.setName(user.getName());
        old.setCountry(user.getCountry());
        return true;
    }

    // GET country with an given code
    public Country getCountry(String countryCode) {
        for (Country country : countryList) {
            if (country.getCountryCode().equals(countryCode)) {
                return country;
            }
        }
        return null;
    }

    // DELETE country with a given country code
    public void deleteCountry(String countryCode) {
        Country country = getCountry(countryCode);
        if (country == null) {
            return;
        }

        countryList.remove(country);
    }

    // Add country
    public boolean addCountry(Country country) {
        if (this.getCountry(country.getCountryCode()) != null) {
            return false;
        }
        countryList.add(country);
        return true;
    }

    // Update country
    public boolean updateCountry(Country country) {
        Country old = this.getCountry(country.getCountryCode());
        if (old == null) {
            return false;
        }
        old.setCountryName(country.getCountryName());
        old.setCountryCode(country.getCountryCode());
        return true;
    }


    // GET all the jobs
    public List<Job> getAllJobs() {
        return jobsList;
    }

    // GET jobs in a given category
    public List<Job> getJobsByCategory(String category) {
        List<Job> filtered = new ArrayList<>();
        for (Job job : jobsList) {
            if (job.getCategory().equals(category)) {
                filtered.add(job);
            }
        }
        return filtered;
    }

    // GET all jobs for a given category
    public List<Job> getJobs(String category) {
        List<Job> filtered = new ArrayList<>();
        for (Job job : jobsList) {
            if (job.getCategory().equals(category)) {
                filtered.add(job);
            }
        }
        return filtered;
    }

    // GET the job with the given jobName
    public Job getJob(String jobName) {
        for (Job job : jobsList) {
            if (job.getJobName().equals(jobName)) {
                return job;
            }
        }
        return null;
    }

    // DELETE job with a given job name
    public void deleteJob(String jobName) {
        Job job = getJob(jobName);
        if (job == null) {
            return;
        }

        jobsList.remove(job);
    }

    // Add new job
    public boolean createJob(Job job) {
        if (this.getJob(job.getJobName()) != null) {
            return false;
        }
        jobsList.add(job);
        return true;
    }

    // Update job
    public boolean updateJob(Job job) {
        Job old = this.getJob(job.getJobName());
        if (old == null) {
            return false;
        }
        old.setJobName(job.getJobName());
        old.setCategory(job.getCategory());
        old.setPayRate(job.getPayRate());
        old.setEstimatedTime(job.getEstimatedTime());
        return true;
    }
}

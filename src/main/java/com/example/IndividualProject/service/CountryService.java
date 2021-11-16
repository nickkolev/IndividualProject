package com.example.IndividualProject.service;

import com.example.IndividualProject.model.Country;
import com.example.IndividualProject.repository.CountryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CountryService {
    private final CountryRepository countryRepository;

    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    public Country getCountryById(String id) {
        return countryRepository.findById(id).orElse(null);
    }

    public Country addCountry(Country country) {
        return countryRepository.insert(country);
    }

    public Country updateCountry(Country country) {
        return countryRepository.save(country);
    }

    public void deleteCountryById(String id){
        countryRepository.deleteById(id);
    }
}

package com.example.IndividualProject.controller;

import com.example.IndividualProject.model.*;

import com.example.IndividualProject.repository.FakeIData;
import com.example.IndividualProject.repository.RealIData;
import com.example.IndividualProject.service.CountryService;
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
@RequestMapping("/countries")
public class CountryController {

    @Autowired
    private final CountryService countryService;

    @GetMapping("/hello")
    @ResponseBody
    public String SayHello() {
        return "Hello, your resources work!";
    }

    @GetMapping
    public ResponseEntity<List<Country>> getAllCountries() {

        List<Country> countries = countryService.getAllCountries();

        if(countries != null) {
            return ResponseEntity.ok().body(countries);
        } else {
            return new ResponseEntity("There are no countries added", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Country> getCountry(@PathVariable(value = "id") String id) {
        Country country = countryService.getCountryById(id);

        if (country != null) {
            return ResponseEntity.ok().body(country);
        } else {
            return new ResponseEntity("Country with ID " + id + " does not exists.", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public ResponseEntity<Country> createCountry(@RequestBody Country country) {
        Country created = countryService.addCountry(country);

        if (created == null) {
            String entity = "Country with ID " + country.getId() + " already exists.";
            return new ResponseEntity(entity, HttpStatus.CONFLICT);
        }

        String url = "country" + "/" + created.getId();
        URI uri = URI.create(url);
        return new ResponseEntity(uri, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Country> updateCountry(
            @PathVariable("id") String id, @RequestBody Country country) {
        Country old = countryService.getCountryById(id);

        if (old == null) {
            return new ResponseEntity("Country not found.", HttpStatus.NOT_FOUND);
        }

        old.setCountryName(country.getCountryName());
        old.setCountryCode(country.getCountryCode());
        old.setEU(country.isEU());
        Country updated = countryService.updateCountry(old);
        return ResponseEntity.ok().body(updated);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteCountry(@PathVariable String id) {
        Country country = countryService.getCountryById(id);

        if(country == null){
            return new ResponseEntity("Country not found.", HttpStatus.NOT_FOUND);
        }

        countryService.deleteCountryById(id);
        return ResponseEntity.ok().build();
    }
}

package com.example.IndividualProject.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "countries")
public class Country {

    @Id
    private String id;
    private String countryName;
    private String countryCode;
    private boolean EU;

    public Country(String countryName, String countryCode, boolean EU) {
        this.countryName = countryName;
        this.countryCode = countryCode;
        this.EU = EU;
    }

    public Country() {
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public boolean isEU() {
        return EU;
    }

    public void setEU(boolean EU) {
        this.EU = EU;
    }

    @Override
    public String toString() {
        return String.format("Country: %s%n" +
                "Country code: %s%n" +
                "EU: %b", countryName, countryCode, EU);
    }
}

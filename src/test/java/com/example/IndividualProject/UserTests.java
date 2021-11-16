package com.example.IndividualProject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.example.IndividualProject.model.*;
import com.example.IndividualProject.repository.*;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserTests {

    @Test
    public void CalculateAverageYears() {
        // arrange
        FakeIData data = new FakeIData();
        User user = data.getUser("3");
        // act
        double result = user.CalculateAverageYears();
        // assert
        Assertions.assertEquals(30,result);
    }
}

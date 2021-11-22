package com.example.IndividualProject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.example.IndividualProject.model.*;
import com.example.IndividualProject.repository.*;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JobTests {

    @Test
    public void calculateAveragePayRateCategory() {
        // arrange
        FakeIData data = new FakeIData();
        Job job = data.getJob("Software Engineer");
        // act
        double result = job.CalculateAveragePayRate();
        // assert
        Assertions.assertEquals(42.5,result);



    }
}

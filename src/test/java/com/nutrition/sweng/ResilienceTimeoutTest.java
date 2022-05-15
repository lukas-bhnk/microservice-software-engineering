/*
package com.nutrition.sweng;

import com.nutrition.sweng.Model.Food;
import com.nutrition.sweng.Model.Minerals;
import com.nutrition.sweng.Model.NutritionalValues;
import com.nutrition.sweng.Model.Vitamins;
import com.nutrition.sweng.Service.FoodInfoServiceClient;
import feign.RetryableException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
public class ResilienceTimeoutTest {

    @Autowired
    private FoodInfoServiceClient foodInfoServiceClient;

    @Test
    public void testMethodsInfoDelayedThrowsTimeoutException() {
        assertThrows(RetryableException.class, () -> {
            Food food = foodInfoServiceClient.getFood("20");
            Minerals minerals = foodInfoServiceClient.getMinerals("20");
            Vitamins vitamins = foodInfoServiceClient.getVitamins("20");
            NutritionalValues nutritionalValues = foodInfoServiceClient.getNutritionalValues("20");
        });
    }
}
*/

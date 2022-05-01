package com.nutrition.sweng.Persistence;

import com.nutrition.sweng.Model.NutritionalValues;
import com.nutrition.sweng.Service.FoodInfoServiceClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FoodInfoServiceTests {

    @Autowired
    private FoodInfoServiceClient foodInfoServiceClient;

    @Test
    public void shouldCanGetNutritionalValues() {
        NutritionalValues nutritionalValues = foodInfoServiceClient.getNutritionalValues("2");
        assert (nutritionalValues.getId()==2);
    }
}

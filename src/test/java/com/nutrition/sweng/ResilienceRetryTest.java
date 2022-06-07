package com.nutrition.sweng;

import com.nutrition.sweng.Model.Food;
import com.nutrition.sweng.Model.NutritionalValues;
import com.nutrition.sweng.Model.ResourceNotFoundException;
import com.nutrition.sweng.Service.FoodInfoServiceClient;
import com.nutrition.sweng.Service.FoodService;
import com.nutrition.sweng.Service.MealService;
import feign.RetryableException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@ActiveProfiles("test")
public class ResilienceRetryTest {
    @MockBean
    private FoodInfoServiceClient foodInfoServiceClient;

    @Autowired
    private MealService mealService;

    @Autowired
    private FoodService foodService;

/*    @Test
    public void testGetInfosFromDelayedThrowsRetryableException() {
        given(foodInfoServiceClient.getFood("YEAAAAHHHHHHH")).willThrow(feign.RetryableException.class);
        given(foodInfoServiceClient.getNutritionalValues("JUHHHUUU")).willThrow(feign.RetryableException.class);
        given(foodInfoServiceClient.getVitamins("Software")).willThrow(feign.RetryableException.class);
        given(foodInfoServiceClient.getNutritionalValues("JUHHHUUU")).willThrow(feign.RetryableException.class);
        given(foodInfoServiceClient.getMinerals("Engineering")).willThrow(feign.RetryableException.class);
    }*/

    @Test
    public void testGetNutritionalValuesDelayedThrowsTimeoutException(){
        given(mealService.getNutritionalValues(20L)).willThrow(ResourceNotFoundException.class);
    }

/*    @Test
    public void testGetAllFoodInfos(){
        String info = foodService.getAllFoodInfos(1000L);
        assertEquals(foodService.NO_FOOD_INFO, info);
    }*/
}

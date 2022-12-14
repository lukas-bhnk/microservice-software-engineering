package com.nutrition.sweng;

import com.nutrition.sweng.Model.Joke;
import com.nutrition.sweng.Service.JokeServiceClient;
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
    private JokeServiceClient jokeServiceClient;

    @Autowired
    private MealService mealService;


    @Test
    public void testGetJokeFromDelayedThrowsRetryableException() {
        given(jokeServiceClient.getJoke("YEAAAAHHHHHHH")).willThrow(feign.RetryableException.class);
    }


    @Test
    public void testGetJokeThrowsTimeoutException(){
        given(jokeServiceClient.getJoke("Hallo Herr Prof. Dr. Thöne")).willThrow(RetryableException.class);
        Joke joke = mealService.queryJoke("Hallo Herr Prof. Dr. Thöne");
        assertEquals(mealService.NO_JOKE, joke);
    }

}

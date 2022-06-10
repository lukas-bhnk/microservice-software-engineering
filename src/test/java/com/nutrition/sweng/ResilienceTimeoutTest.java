package com.nutrition.sweng;

import com.nutrition.sweng.Model.*;
import com.nutrition.sweng.Service.JokeServiceClient;
import feign.RetryableException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("timeout")
public class ResilienceTimeoutTest {

    @Autowired
    private JokeServiceClient jokeServiceClient;

    @Test
    public void testMethodsInfoDelayedThrowsTimeoutException() {
        assertThrows(RetryableException.class, () -> {
            Joke joke = jokeServiceClient.getJoke("Hello World");
        });
    }
}

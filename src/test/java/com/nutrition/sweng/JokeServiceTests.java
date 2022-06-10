package com.nutrition.sweng;

import com.nutrition.sweng.Model.*;
import com.nutrition.sweng.Service.JokeServiceClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
@ActiveProfiles("test")
public class JokeServiceTests {

    @Autowired
    private JokeServiceClient jokeServiceClient;

    @Test
    public void shouldCanGetJoke(){
        Joke joke = jokeServiceClient.getJoke("Programming");
        assertFalse(joke.getJoke().isEmpty());
        assertNotEquals("", joke.getJoke());
    }



}

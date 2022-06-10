package com.nutrition.sweng.Service;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.nutrition.sweng.Model.Joke;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

@FeignClient(url = "${joke.service.error.url}", name = "JokeService")
public interface JokeServiceClient {
    /**
     * get a joke by a specific category.
     * @param category
     * @return joke
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{category}?lang=de&type=single")
    public Joke getJoke(@PathVariable("category") String category);

}

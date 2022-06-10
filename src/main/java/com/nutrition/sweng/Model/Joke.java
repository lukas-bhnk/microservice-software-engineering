package com.nutrition.sweng.Model;

import java.util.Map;

public class Joke {

    public boolean error;
    public String category;
    public String type;
    public String joke;
    public Map<String, Boolean> flags;
    public int id;
    public boolean safe;
    public String lang;


    public Joke() {}

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }
}

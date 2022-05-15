/*
package com.nutrition.sweng;

import com.nutrition.sweng.Model.*;
import com.nutrition.sweng.Service.FoodInfoServiceClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FoodInfoServiceTests {

    @Autowired
    private FoodInfoServiceClient foodInfoServiceClient;

    @Test
    public void shouldCanGetMinerals(){
        Minerals minerals = foodInfoServiceClient.getMinerals("2");
        assert(minerals.getId()==2);
        assert (minerals.getChloride()==1);
        assert (minerals.getIron()==1);
        assert (minerals.getMagnesium()==9);
        assert (minerals.getPhosphorus()==15);
        assert (minerals.getPotassium()==29);
        assert (minerals.getSelenium()==310);
        assert (minerals.getSodium()==212);
        assert (minerals.getZinc()==33);
    }


    @Test
    public void shouldCanGetNutritionalValues() {
        NutritionalValues nutritionalValues = foodInfoServiceClient.getNutritionalValues("2");
        assert (nutritionalValues.getId()==2);
        assert (nutritionalValues.getFats()==40);
        assert (nutritionalValues.getCarbs()==20);
        assert (nutritionalValues.getProteins()==23);
        assert (nutritionalValues.getCalories()==39);
        assert (nutritionalValues.getAlcohol()==9);
        assert (nutritionalValues.getFatsSaturated()==12);
        assert (nutritionalValues.getSalt()==1);
        assert (nutritionalValues.getSugar()==1);
    }

    @Test
    public void shouldCanGetVitamins(){
        Vitamins vitamins = foodInfoServiceClient.getVitamins("2");
        assert (vitamins.getId()==2);
        assert (vitamins.getA()==112);
        assert (vitamins.getB1()==25);
        assert (vitamins.getB2()==1);
        assert (vitamins.getB6()==13);
        assert (vitamins.getB12()==12);
        assert (vitamins.getC()==5);
        assert (vitamins.getD()==25);
        assert (vitamins.getE()==6);
        assert (vitamins.getBetacarotin()==13);
        assert (vitamins.getFol()==34);
        assert (vitamins.getNiacin()==42);
        assert (vitamins.getRetinol()==23);
        assert (vitamins.getK()==22);
    }

    @Test
    public void shouldCanGetFood(){
        Food food = foodInfoServiceClient.getFood("2");
        System.out.println(food.getName());
        assert (food.getId()==2);
        assert (food.getName().equals("Cola"));
        assert (food.getUnitSize()== FoodUnitSize.MILLILITRE);
    }

}
*/

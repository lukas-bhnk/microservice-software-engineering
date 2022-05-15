package com.nutrition.sweng;

import com.nutrition.sweng.Controller.MealController;
import com.nutrition.sweng.Controller.NutritionalValuesController;
import com.nutrition.sweng.Model.Food;
import com.nutrition.sweng.Model.FoodUnitSize;
import com.nutrition.sweng.Model.Meal;
import com.nutrition.sweng.Model.NutritionalValues;
import com.nutrition.sweng.Service.MealService;
import com.nutrition.sweng.Service.NutritionalValuesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(NutritionalValuesController.class)
public class NutritionalValuesControllerTests {


    @Autowired
    private MockMvc mvc;

    @MockBean
    private NutritionalValuesService nutritionalValuesService;

    private Food food;
    private NutritionalValues nutritionalValues;


    @BeforeEach
    public void setUp() throws Exception {
        this.food = new Food(1L, "banane", FoodUnitSize.GRAMS, Collections.emptySet());
        this.nutritionalValues = new NutritionalValues(1L, 1.0, 2.0, 0, 0.0, 0.0, 0.0, 0.0, 0.0, food);
    }

    @Test
    public void getNutritionalValues() throws Exception {
        given(this.nutritionalValuesService.getNutritionalValues(1L)).willReturn(this.nutritionalValues);
        this.mvc.perform(get("/rest/nutritionalValues/{id}", 1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(content().json("{\"id\":1,\"carbs\":1.0,\"proteins\":2.0,\"calories\":0.0,\"sugar\":0.0,\"fats\":0.0,\"fatsSaturated\":0.0,\"alcohol\":0.0,\"salt\":0.0}"));
    }
}

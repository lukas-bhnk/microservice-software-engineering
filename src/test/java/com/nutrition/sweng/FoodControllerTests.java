package com.nutrition.sweng;

import com.nutrition.sweng.Controller.FoodController;
import com.nutrition.sweng.Controller.MineralsController;
import com.nutrition.sweng.Model.*;
import com.nutrition.sweng.Service.FoodService;
import com.nutrition.sweng.Service.MineralsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FoodController.class)
@ActiveProfiles("test")
public class FoodControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private FoodService foodService;

    private Minerals minerals;
    private NutritionalValues nutritionalValues;
    private Vitamins vitamins;
    private Food food;

    @BeforeEach
    public void setUp() throws Exception {
        this.food = new Food(1L, "banane", FoodUnitSize.GRAMS, Collections.emptySet());
    }
    @Test
    public void getFood() throws Exception {
        given(this.foodService.getFood(1L)).willReturn(this.food);
        this.mvc.perform(get("/rest/food/{id}", 1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(content().json("{\"id\":0,\"name\":\"banane\",\"unitSize\":\"pro 100g essbarer Anteil\"}"));
    }
}

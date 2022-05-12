package com.nutrition.sweng;

import com.nutrition.sweng.Controller.MealController;
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

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(NutritionalValuesControllerTests.class)
public class NutritionalValuesControllerTests {


    @Autowired
    private MockMvc mvc;

    @MockBean
    private NutritionalValuesService nutritionalValuesService;

    private NutritionalValues nutritionalValues;

    @BeforeEach
    public void setUp() throws Exception {
        this.nutritionalValues = nutritionalValuesService.getNutritionalValues(1L);
    }

    @Test
    public void getNutritionalValues() throws Exception {
        given(this.nutritionalValuesService.getNutritionalValues(1L)).willReturn(this.nutritionalValues);
        this.mvc.perform(get("/rest/nutritionalValues/{}", 1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(content().json("{\"id\":1,\"carbs\":-1.0,\"proteins\":23.0,\"calories\":39.0,\"sugar\":10.0,\"fats\":40.0,\"fatsSaturated\":12.0,\"alcohol\":9.0,\"salt\":1.0}"));
    }
}

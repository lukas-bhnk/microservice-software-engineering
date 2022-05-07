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
    public void getMeal() throws Exception {
        given(this.nutritionalValuesService.getNutritionalValues(1L)).willReturn(this.nutritionalValues);
        this.mvc.perform(get("/rest/nutritionalValues/{}", 1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(content().json("{\"id\":2,\"date\":\"2020-03-19T23:00:00.000+00:00\",\"proteins\":43.0,\"carbs\":33.0,\"fats\":20.0,\"calories\":11,\"mealCategory\":\"SNACK\",\"foodList\":[{\"id\":1,\"name\":\"Apfel\",\"unitSize\":\"pro 100g essbarer Anteil\"},{\"id\":2,\"name\":\"Cola\",\"unitSize\":\"pro 100ml\"}]}"));
    }
}

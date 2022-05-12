package com.nutrition.sweng;

import com.nutrition.sweng.Controller.MealController;
import com.nutrition.sweng.Controller.VitaminsController;
import com.nutrition.sweng.Model.Meal;
import com.nutrition.sweng.Model.NutritionalValues;
import com.nutrition.sweng.Model.Vitamins;
import com.nutrition.sweng.Service.MealService;
import com.nutrition.sweng.Service.NutritionalValuesService;
import com.nutrition.sweng.Service.VitaminsService;
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

@WebMvcTest(VitaminsController.class)
public class VitaminsControllerTests {


    @Autowired
    private MockMvc mvc;

    @MockBean
    private VitaminsService vitaminsService;

    private Vitamins vitamins;

    @BeforeEach
    public void setUp() throws Exception {
        this.vitamins = vitaminsService.getVitamins(1L);
    }
    @Test
    public void getVitamins() throws Exception {
        given(this.vitaminsService.getVitamins(1L)).willReturn(this.vitamins);
        this.mvc.perform(get("/rest/vitamins/{}", 1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(content().json("{\"id\":1,\"c\":1.0,\"fol\":7.0,\"a\":12.0,\"b1\":123.0,\"b2\":12.0,\"b6\":12.0,\"b12\":14.0,\"d\":5.0,\"e\":6.0,\"k\":45.0,\"betacarotin\":13.0,\"niacin\":45.0,\"retinol\":23.0}"));
    }
}

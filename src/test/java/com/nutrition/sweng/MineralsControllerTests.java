package com.nutrition.sweng;

import com.nutrition.sweng.Controller.MineralsController;
import com.nutrition.sweng.Model.Food;
import com.nutrition.sweng.Model.FoodUnitSize;
import com.nutrition.sweng.Model.Minerals;
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

@WebMvcTest(MineralsController.class)
@ActiveProfiles("test")
public class MineralsControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MineralsService mineralsService;

    private Minerals minerals;
    private Food food;

    @BeforeEach
    public void setUp() throws Exception {
        this.food = new Food(1L, "banane", FoodUnitSize.GRAMS, Collections.emptySet());
        this.minerals = new Minerals(1L,0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, food);
    }
    @Test
    public void getMinerals() throws Exception {
        given(this.mineralsService.getMinerals(1L)).willReturn(this.minerals);
        this.mvc.perform(get("/rest/minerals/{id}", 1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(content().json("{\"id\":1,\"chloride\":0.0,\"magnesium\":0.0,\"phosphorus\":0.0,\"iron\":0.0,\"potassium\":0.0,\"selenium\":0.0,\"sodium\":0.0,\"zinc\":0.0}"));
    }
}

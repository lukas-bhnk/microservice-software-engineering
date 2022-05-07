package com.nutrition.sweng;

import com.nutrition.sweng.Controller.MineralsController;
import com.nutrition.sweng.Model.Minerals;
import com.nutrition.sweng.Service.MineralsService;
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

@WebMvcTest(MineralsController.class)
public class MineralsControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MineralsService mineralsService;

    private Minerals minerals;

    @BeforeEach
    public void setUp() throws Exception {
        this.minerals = mineralsService.getMinerals(1L);
    }
    @Test
    public void getMeal() throws Exception {
        given(this.mineralsService.getMinerals(1L)).willReturn(this.minerals);
        this.mvc.perform(get("/rest/minerals/{}", 1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(content().json("{\"id\":2,\"date\":\"2020-03-19T23:00:00.000+00:00\",\"proteins\":43.0,\"carbs\":33.0,\"fats\":20.0,\"calories\":11,\"mealCategory\":\"SNACK\",\"foodList\":[{\"id\":1,\"name\":\"Apfel\",\"unitSize\":\"pro 100g essbarer Anteil\"},{\"id\":2,\"name\":\"Cola\",\"unitSize\":\"pro 100ml\"}]}"));
    }
}

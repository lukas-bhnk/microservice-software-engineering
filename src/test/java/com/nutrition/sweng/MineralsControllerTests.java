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
    public void getMinerals() throws Exception {
        given(this.mineralsService.getMinerals(1L)).willReturn(this.minerals);
        this.mvc.perform(get("/rest/minerals/{}", 1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(content().json("{\"id\":1,\"chloride\":2.0,\"magnesium\":6.0,\"phosphorus\":5.0,\"iron\":3.0,\"potassium\":9.0,\"selenium\":10.0,\"sodium\":12.0,\"zinc\":33.0}"));
    }
}

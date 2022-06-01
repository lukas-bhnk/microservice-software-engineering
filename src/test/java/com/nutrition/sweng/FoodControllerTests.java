package com.nutrition.sweng;

import com.nutrition.sweng.Controller.FoodController;
import com.nutrition.sweng.Controller.MineralsController;
import com.nutrition.sweng.Model.*;
import com.nutrition.sweng.Service.FoodService;
import com.nutrition.sweng.Service.MineralsService;
import com.nutrition.sweng.security.JwtValidator;
import org.apache.commons.compress.utils.IOUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.HashSet;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FoodController.class)
@ActiveProfiles("test")
public class FoodControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private FoodService foodService;

    @MockBean
    private JwtValidator jwtValidator;

    private Minerals minerals;
    private NutritionalValues nutritionalValues;
    private Vitamins vitamins;
    private Food food;
    private final String AUTH_HEADER = "Bearer ANY-JWT-STRING";;
    private final String email = "ab@domain.com";

    @BeforeEach
    public void setUp() throws Exception {

        UserDetails userDetails = org.springframework.security.core.userdetails.User.withUsername(email)
                .password("***")
                .authorities(Role.ADMIN.getAuthority())
                .build();

        given(jwtValidator.isValidJWT(any(String.class))).willReturn(true);
        given(jwtValidator.getUserEmail(any(String.class))).willReturn(email);
        given(jwtValidator.resolveToken(any(HttpServletRequest.class))).willReturn(AUTH_HEADER.substring(7));
        given(jwtValidator.getAuthentication(any(String.class))).willReturn(new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities()));

        this.food = new Food(1L, "banane", FoodUnitSize.GRAMS,  new HashSet<FoodEntry>());

        food.setMinerals(minerals);
        food.setVitamins(vitamins);
        food.setNutritionalValues(nutritionalValues);
        this.minerals = new Minerals(1L,0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, food);
        this.nutritionalValues = new NutritionalValues(1L, 1.0, 2.0, 0, 0.0, 0.0, 0.0, 0.0, 0.0, food);
        this.vitamins = new Vitamins(1L,0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, food);
    }
    @Test
    public void getFood() throws Exception {
        given(this.foodService.getFood(1L)).willReturn(this.food);
        this.mvc.perform(get("/rest/food/{id}", 1)
                .header("Authorization",this.AUTH_HEADER))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(content().json("{\"id\":1,\"name\":\"banane\",\"unitSize\":\"pro 100g essbarer Anteil\"}"));
    }

    @Test
    public void postExcel() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "Schweizer-Nahrwertdatenbank-test.xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", new ClassPathResource("Schweizer-Nahrwertdatenbank-test.xlsx").getInputStream());
        System.out.println(file.toString());
        mvc.perform(multipart("/rest/food").file(file)
                .header("Authorization",this.AUTH_HEADER))
                .andExpect(status().isOk())
                .andExpect(content().string("Successfully processed the posted file"));
    }
}

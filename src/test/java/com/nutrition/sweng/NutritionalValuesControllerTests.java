package com.nutrition.sweng;

import com.nutrition.sweng.Controller.MealController;
import com.nutrition.sweng.Controller.NutritionalValuesController;
import com.nutrition.sweng.Model.*;
import com.nutrition.sweng.Service.MealService;
import com.nutrition.sweng.Service.NutritionalValuesService;
import com.nutrition.sweng.security.JwtValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(NutritionalValuesController.class)
@ActiveProfiles("test")
public class NutritionalValuesControllerTests {


    @Autowired
    private MockMvc mvc;

    @MockBean
    private NutritionalValuesService nutritionalValuesService;

    @MockBean
    private JwtValidator jwtValidator;

    private Food food;
    private NutritionalValues nutritionalValues;
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

        this.food = new Food(1L, "banane", FoodUnitSize.GRAMS, Collections.emptySet());
        this.nutritionalValues = new NutritionalValues(1L, 1.0, 2.0, 0, 0.0, 0.0, 0.0, 0.0, 0.0, food);
    }

    @Test
    public void getNutritionalValues() throws Exception {
        given(this.nutritionalValuesService.getNutritionalValues(1L)).willReturn(this.nutritionalValues);
        this.mvc.perform(get("/rest/nutritionalValues/{id}", 1)
                .header("Authorization",this.AUTH_HEADER))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(content().json("{\"id\":1,\"carbs\":1.0,\"proteins\":2.0,\"calories\":0.0,\"sugar\":0.0,\"fats\":0.0,\"fatsSaturated\":0.0,\"alcohol\":0.0,\"salt\":0.0}"));
    }
}

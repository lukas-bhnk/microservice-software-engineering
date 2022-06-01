package com.nutrition.sweng;

import com.nutrition.sweng.Controller.MealController;
import com.nutrition.sweng.Controller.VitaminsController;
import com.nutrition.sweng.Model.*;
import com.nutrition.sweng.Service.MealService;
import com.nutrition.sweng.Service.NutritionalValuesService;
import com.nutrition.sweng.Service.VitaminsService;
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

@WebMvcTest(VitaminsController.class)
@ActiveProfiles("test")
public class VitaminsControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private JwtValidator jwtValidator;

    @MockBean
    private VitaminsService vitaminsService;

    private Food food;

    private Vitamins vitamins;
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
        this.vitamins = new Vitamins(1L,0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, food);
    }
    @Test
    public void getVitamins() throws Exception {
        given(this.vitaminsService.getVitamins(1L)).willReturn(this.vitamins);
        this.mvc.perform(get("/rest/vitamins/{id}", 1)
                .header("Authorization",this.AUTH_HEADER))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(content().json("{\"id\":1,\"c\":0.0,\"fol\":0.0,\"a\":0.0,\"b1\":0.0,\"b2\":0.0,\"b6\":0.0,\"b12\":0.0,\"d\":0.0,\"e\":0.0,\"k\":0.0,\"betacarotin\":0.0,\"niacin\":0.0,\"retinol\":0.0}"));
    }
}

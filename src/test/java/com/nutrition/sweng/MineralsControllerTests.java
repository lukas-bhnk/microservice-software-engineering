package com.nutrition.sweng;

import com.nutrition.sweng.Controller.MineralsController;
import com.nutrition.sweng.Model.Food;
import com.nutrition.sweng.Model.FoodUnitSize;
import com.nutrition.sweng.Model.Minerals;
import com.nutrition.sweng.Model.Role;
import com.nutrition.sweng.Service.MineralsService;
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

@WebMvcTest(MineralsController.class)
@ActiveProfiles("test")
public class MineralsControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MineralsService mineralsService;

    @MockBean
    private JwtValidator jwtValidator;

    private Minerals minerals;
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

        this.food = new Food(1L, "banane", FoodUnitSize.GRAMS, Collections.emptySet());
        this.minerals = new Minerals(1L,0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, food);
    }
    @Test
    public void getMinerals() throws Exception {
        given(this.mineralsService.getMinerals(1L)).willReturn(this.minerals);
        this.mvc.perform(get("/rest/minerals/{id}", 1)
                .header("Authorization",this.AUTH_HEADER))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(content().json("{\"id\":1,\"chloride\":0.0,\"magnesium\":0.0,\"phosphorus\":0.0,\"iron\":0.0,\"potassium\":0.0,\"selenium\":0.0,\"sodium\":0.0,\"zinc\":0.0}"));
    }
}

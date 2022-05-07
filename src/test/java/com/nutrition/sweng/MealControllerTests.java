package com.nutrition.sweng;

import com.nutrition.sweng.Controller.MealController;
import com.nutrition.sweng.Model.Food;
import com.nutrition.sweng.Model.Meal;
import com.nutrition.sweng.Model.MealCategory;
import com.nutrition.sweng.Service.MealService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MealController.class)
public class MealControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MealService mealService;

    private Meal meal;

    @BeforeEach
    public void setUp() throws Exception {
        this.meal = mealService.getMeal(1L);
    }
    @Test
    public void getMeal() throws Exception {
        given(this.mealService.getMeal(1L)).willReturn(this.meal);
        this.mvc.perform(get("/rest/meal/{}", 1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(content().json("{\"id\":2,\"date\":\"2020-03-19T23:00:00.000+00:00\",\"proteins\":43.0,\"carbs\":33.0,\"fats\":20.0,\"calories\":11,\"mealCategory\":\"SNACK\",\"foodList\":[{\"id\":1,\"name\":\"Apfel\",\"unitSize\":\"pro 100g essbarer Anteil\"},{\"id\":2,\"name\":\"Cola\",\"unitSize\":\"pro 100ml\"}]}"));
    }

    @Test
    public void addFood() throws Exception{
        given(this.mealService.addFood(1L, 1L, 120)).willReturn(this.meal);
        this.mvc.perform(post("/rest/meal/{mealId}/{foodId}/{quantity}", 1, 2, 1233))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{'id':null,'name':'Senf','status':'OPEN','price':'1,99'}"));
    }

    @Test
    public void createMeal() throws Exception{
        given(this.mealService.createMeal(new Date(), MealCategory.BREAKFAST, 120)).willReturn(this.meal);
        this.mvc.perform(post("/rest/meal/{mealId}/{foodId}/{quantity}", 1, 2, 1233))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{'id':null,'name':'Senf','status':'OPEN','price':'1,99'}"));
    }

    @Test
    public void updateQuantity() throws Exception {
        given(this.mealService.updateQuantity(1L, 2L, 200)).willReturn(this.meal);
        this.mvc.perform(patch("/rest/shoppingList/{id}?done=true", 1))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void deleteFood() throws Exception {
        this.mvc.perform(delete("/rest/meal/{mealId}/{foodId}", 1, 1)).andDo(print())
                .andExpect(status().isOk());
    }
}
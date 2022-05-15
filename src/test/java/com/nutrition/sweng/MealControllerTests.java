package com.nutrition.sweng;

import com.nutrition.sweng.Controller.MealController;
import com.nutrition.sweng.Controller.MineralsController;
import com.nutrition.sweng.Model.*;
import com.nutrition.sweng.Service.MealService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.text.SimpleDateFormat;
import java.util.*;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MealController.class)
@ActiveProfiles("test")
public class MealControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MealService mealService;

    private Meal meal;
    private List<Meal> meals;
    private Set<FoodEntry> foodEntries;
    private User user;
    private Food food;
    private NutritionalValues nutritionalValues;
    private Minerals minerals;
    private Vitamins vitamins;
    private final String email = "peter@gmail.com";


    @BeforeEach
    public void setUp() throws Exception {
        this.meals = new ArrayList<Meal>();
        this.meal = new Meal();
        this.foodEntries = new HashSet<FoodEntry>();
        this.food = new Food(1L, "banane", FoodUnitSize.GRAMS, foodEntries);
        this.nutritionalValues = new NutritionalValues(1L, 0.0, 0.0, 0, 0.0, 0.0, 0.0, 0.0, 0.0, food);
        this.user = new User("peter", email);
        this.vitamins = new Vitamins(1L,0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, food);
        this.minerals = new Minerals(1L,0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, food);
    }
    @Test
    public void getMeal() throws Exception {
        Meal meal = new Meal(1L, new SimpleDateFormat("yyyy-MM-dd").parse(2021+"-"+05+"-"+15), 12,  MealCategory.BREAKFAST,2.0,2.0,2.0, user, foodEntries);
        this.meal = meal;
        given(this.mealService.getMeal(1L)).willReturn(meal);
        this.mvc.perform(get("/rest/meal/{id}", 1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"date\":\"2021-05-15T00:00:00.000+00:00\",\"proteins\":2.0,\"carbs\":2.0,\"fats\":2.0,\"calories\":12,\"mealCategory\":\"BREAKFAST\",\"foodEntries\":[]}"));
    }

    @Test
    public void getDailyMeals() throws Exception{
        Meal meal = new Meal(1L, new SimpleDateFormat("yyyy-MM-dd").parse(2021+"-"+05+"-"+15), 12,  MealCategory.BREAKFAST,2.0,2.0,2.0, user, foodEntries);
        Meal meal2 = new Meal(2L, new SimpleDateFormat("yyyy-MM-dd").parse(2021+"-"+05+"-"+15), 12,  MealCategory.DINNER,2.0,2.0,2.0, user, foodEntries);
        this.meals.add(meal);
        this.meals.add(meal2);
        given(this.mealService.getDailyMeals(new SimpleDateFormat("yyyy-MM-dd").parse(2021+"-"+05+"-"+15), "peter@gmail.com")).willReturn(meals);
        this.mvc.perform(get("/rest/meal/{day}/{month}/{year}/{email}", 15, 05, 2021, email))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1,\"date\":\"2021-05-15T00:00:00.000+00:00\",\"calories\":12,\"mealCategory\":\"BREAKFAST\",\"fats\":2.0,\"carbs\":2.0,\"proteins\":2.0,\"userFk\":{\"id\":0,\"name\":\"peter\",\"email\":\"peter@gmail.com\"}},{\"id\":2,\"date\":\"2021-05-15T00:00:00.000+00:00\",\"calories\":12,\"mealCategory\":\"DINNER\",\"fats\":2.0,\"carbs\":2.0,\"proteins\":2.0,\"userFk\":{\"id\":0,\"name\":\"peter\",\"email\":\"peter@gmail.com\"}}]"));
    }

    @Test
    public void addUpdateDeleteFood() throws Exception{
        Meal meal2 = new Meal(1L, new SimpleDateFormat("yyyy-MM-dd").parse(2021+"-"+05+"-"+15), 3,  MealCategory.BREAKFAST,3.0,3.0,3.0, user, foodEntries);
        Set<FoodEntry> foodEntryList = new HashSet<>();
        FoodEntry foodEntry = new FoodEntry(1L, meal2, food, 100, 1, 1.0, 1.0, 1.0);
        foodEntryList.add(foodEntry);
        meal2.setFoodEntries(foodEntryList);
        given(this.mealService.addFood( 1L, 1L, 100)).willReturn(meal2);
        this.mvc.perform(post("/rest/meal/{mealId}/{foodId}/{quantity}", 1, 1, 100))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"date\":\"2021-05-15T00:00:00.000+00:00\",\"proteins\":3.0,\"carbs\":3.0,\"fats\":3.0,\"calories\":3,\"mealCategory\":\"BREAKFAST\",\"foodEntries\":[{\"id\":1,\"food\":{\"id\":0,\"name\":\"banane\",\"unitSize\":\"pro 100g essbarer Anteil\"},\"quantity\":100,\"calories\":1,\"fats\":1.0,\"carbs\":1.0,\"proteins\":1.0}]}"));
        //update
        foodEntryList.remove(food);
        foodEntry = new FoodEntry(1L, meal2, food, 200, 1, 1.0, 1.0, 1.0);
        foodEntryList.add(foodEntry);
        meal2.setFoodEntries(foodEntryList);
        given(this.mealService.updateQuantity(1L, 1L, 200)).willReturn(meal2);
        this.mvc.perform(patch("/rest/meal/{id}/{food}/{quantity}", 1L, 1L, 200))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"date\":\"2021-05-15T00:00:00.000+00:00\",\"proteins\":3.0,\"carbs\":3.0,\"fats\":3.0,\"calories\":3,\"mealCategory\":\"BREAKFAST\",\"foodEntries\":[{\"id\":1,\"food\":{\"id\":0,\"name\":\"banane\",\"unitSize\":\"pro 100g essbarer Anteil\"},\"quantity\":200,\"calories\":1,\"fats\":1.0,\"carbs\":1.0,\"proteins\":1.0},{\"id\":1,\"food\":{\"id\":0,\"name\":\"banane\",\"unitSize\":\"pro 100g essbarer Anteil\"},\"quantity\":100,\"calories\":1,\"fats\":1.0,\"carbs\":1.0,\"proteins\":1.0}]}"));
        //delete
        meal2.setFoodEntries(Collections.emptySet());
        given(this.mealService.deleteFood(1L, 1L)).willReturn(meal2);
        this.mvc.perform(delete("/rest/meal/{id}/{food}", 1, 1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"date\":\"2021-05-15T00:00:00.000+00:00\",\"proteins\":3.0,\"carbs\":3.0,\"fats\":3.0,\"calories\":3,\"mealCategory\":\"BREAKFAST\",\"foodEntries\":[]}"));


    }


    @Test
    public void createMeal() throws Exception{
        Meal meal = new Meal(1L, new SimpleDateFormat("yyyy-MM-dd").parse(2021+"-"+05+"-"+15), 2,  MealCategory.BREAKFAST,2.0,2.0,2.0, user, foodEntries);
        given(this.mealService.createMeal(new SimpleDateFormat("yyyy-MM-dd").parse(2021+"-"+05+"-"+15), MealCategory.BREAKFAST, email)).willReturn(meal);
        this.mvc.perform(post("/rest/meal/{day}/{month}/{year}/{category}/{email}", 15, 05, 2021,"breakfast",email))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"date\":\"2021-05-15T00:00:00.000+00:00\",\"proteins\":2.0,\"carbs\":2.0,\"fats\":2.0,\"calories\":2,\"mealCategory\":\"BREAKFAST\",\"foodEntries\":[]}"));
    }

}

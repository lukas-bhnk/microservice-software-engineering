package com.nutrition.sweng;

import com.nutrition.sweng.Event.EventPublisher;
import com.nutrition.sweng.Exceptions.ResourceNotFoundException;
import com.nutrition.sweng.Model.*;
import com.nutrition.sweng.Repository.*;
import com.nutrition.sweng.Service.JokeServiceClient;
import com.nutrition.sweng.Service.MealService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class MealServiceTests {

    @Mock
    private FoodRepository foodRepository;
    @Mock
    private MealRepository mealRepository;
    @Mock
    private NutritionalValuesRepository nutritionalValuesRepository;
    @Mock
    private FoodEntryRepository foodEntryRepository;
    @Mock
    private EventPublisher eventPublisher;
    @Mock
    private JokeServiceClient jokeServiceClient;
    @Mock
    private UserRepository userRepository;

    private MealService subject;
    private Food food;
    private Set<FoodEntry> foodList;
    private User user;
    private NutritionalValues nutritionalValues;
    private Meal meal;

    private static final String TEST_FOODNAME = "Banane";
    private static final String TEST_EMAIL = "test@email.com";
    private static final int TEST_QUANTITY = 150;
    private static Date TEST_DATE;

    static {
        try {
            TEST_DATE = new SimpleDateFormat("yyyy-MM-dd").parse(2021+"-"+05+"-"+15);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @BeforeEach
    public void setUp() throws Exception {
        this.subject = new MealService(this.mealRepository,this.jokeServiceClient,this.nutritionalValuesRepository,this.foodRepository, this.foodEntryRepository, this.userRepository, this.eventPublisher);
        this.foodList = new HashSet<>();
        this.food = new Food();
        this.food.setFoodEntries(this.foodList);
        this.food = new Food();
        this.food.setId(1L);
        this.food.setName(TEST_FOODNAME);
        this.food.setUnitSize(FoodUnitSize.GRAMS);
        this.nutritionalValues = new NutritionalValues(1L, 1.0, 2.0, 0, 0.0, 0.0, 0.0, 0.0, 0.0, food);
        this.user = new User("test@email.com");
        this.meal = new Meal(1L, new SimpleDateFormat("yyyy-MM-dd").parse(2021+"-"+05+"-"+15), 12,  MealCategory.BREAKFAST,2.0,2.0,2.0, user, foodList);

    }



    @Test
    public void shouldCreateMeal() throws Exception {
        given(this.userRepository.findByEmail(TEST_EMAIL)).willReturn(Optional.of(this.user));
        Meal meal = this.subject.createMeal(TEST_DATE, MealCategory.BREAKFAST, TEST_EMAIL);
        assertThat(meal.getCalories(), is(0));
    }

    @Test
    public void shouldCreateMealWithNewUser() throws Exception {
        Meal meal = this.subject.createMeal(TEST_DATE, MealCategory.BREAKFAST, "neuemail@mail.com");
        assertThat(meal.getCalories(), is(0));
        assertThat(meal.getCarbs(), is(0.0));
        assertThat(meal.getFats(), is(0.0));
        assertThat(meal.getProteins(), is(0.0));
    }



    @Test
    public void shouldFindAMeal() throws Exception {
        given(this.mealRepository.findByIdAndUser(1L, new User(TEST_EMAIL))).willReturn(Optional.of(this.meal));
        given(this.userRepository.findByEmail(TEST_EMAIL)).willReturn(Optional.of(this.user));
        Meal meal = this.subject.getMeal(1L, TEST_EMAIL);
        assertThat(meal.getId(), is(this.meal.getId()));
        assertThat(meal.getFoodEntries(), is(this.meal.getFoodEntries()));
    }

    @Test
    public void shouldFindDailyMeals() throws Exception {
        given(this.mealRepository.findByDateAndUser(TEST_DATE, new User(TEST_EMAIL))).willReturn(List.of(this.meal));
        given(this.userRepository.findByEmail(TEST_EMAIL)).willReturn(Optional.of(this.user));
        List<Meal> meals = this.subject.getDailyMeals(TEST_DATE, TEST_EMAIL);
        assertThat(meals.size(), is(1));
        assertThat(meals.get(0).getDate(), is(TEST_DATE));
    }


    @Test
    public void shouldTellIfMealIsUnknown() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            this.subject.getMeal(1L, "lalelu");
        });
    }

    @Test
    public void shouldUpdateQuantity() {
        FoodEntry foodEntry = new FoodEntry(1L,this.meal, this.food, 100, 100, 100, 100.0, 100.0);
        this.foodList.add(foodEntry);
        this.meal.setFoodEntries(this.foodList);
        given(this.mealRepository.findByIdAndUser(1L, new User(TEST_EMAIL))).willReturn(Optional.of(this.meal));
        given(this.userRepository.findByEmail(TEST_EMAIL)).willReturn(Optional.of(this.user));
        given(this.nutritionalValuesRepository.findById(1L)).willReturn(Optional.of(this.nutritionalValues));
        Meal meal = this.subject.updateQuantity(1L, 1L, 124, TEST_EMAIL);
        FoodEntry foodEntries = (FoodEntry) meal.getFoodEntries().toArray()[0];
        assertThat(foodEntries.getQuantity(), is(124));

    }


    @Test
    public void shouldSaveFoodEntry() throws Exception {
        given(this.mealRepository.findByIdAndUser(1L, new User(TEST_EMAIL))).willReturn(Optional.of(this.meal));
        given(this.foodRepository.findById(1L)).willReturn(Optional.of(this.food));
        given(this.userRepository.findByEmail(TEST_EMAIL)).willReturn(Optional.of(this.user));
        given(this.nutritionalValuesRepository.findById(1L)).willReturn(Optional.of(this.nutritionalValues));
        Meal meal = this.subject.addFood(1L, 1L, 120, TEST_EMAIL);
        assertThat(meal.getId(), is(this.meal.getId()));
        assertFalse(meal.getFoodEntries().isEmpty());
    }

    @Test
    public void shouldFindAJoke() throws Exception {
        Joke joke = new Joke();
        joke.setJoke("Hallo Herr Prof. Dr. Thöne");
        given(this.jokeServiceClient.getJoke("Softwaree")).willReturn(joke);
        String jokeString = this.subject.getJoke("Softwaree");
        assertThat(jokeString, is("Hallo Herr Prof. Dr. Thöne"));
    }

}

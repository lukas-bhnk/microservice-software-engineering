package com.nutrition.sweng;

import com.nutrition.sweng.Model.*;
import com.nutrition.sweng.Repository.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@DataJpaTest
@ActiveProfiles("test")
public class PersistenceTests {
    @Autowired
    private MealRepository mealRepository;

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private FoodEntryRepository foodEntryRepository;

    @Autowired
    private NutritionalValuesRepository nutritionalValuesRepository;

    @Autowired
    private VitaminsRepository vitaminsRepository;

    @Autowired
    private MineralsRepository mineralsRepository;

    @Autowired
    private UserRepository userRepository;

    private static Date TEST_DATE;

    static {
        try {
            TEST_DATE = new SimpleDateFormat("yyyy-MM-dd").parse("2022-04-10");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private User user;

    @BeforeEach
    public void setUp() throws Exception {
        user = userRepository.findByEmail("ab@domain.com").get();
    }

    @Test
    public void findMealById() {
        Optional<Meal> mealOptional = mealRepository.findByIdAndUser(1L, user);
        Meal meal = mealOptional.get();
        assert (meal.getId()==1L);
        Optional<Meal> mealOptional2 = mealRepository.findByIdAndUser(10L, user);
        assertFalse (mealOptional2.isPresent());
    }

    @Test
    public void findByDate() {
        List<Meal> mealList = mealRepository.findByDateAndUser(TEST_DATE, user);
        assertFalse (mealList.isEmpty());
        List<Meal> mealList2 = mealRepository.findByDateAndUser(new Date(), user);
        assertTrue (mealList2.isEmpty());
    }


/*
    Test database connection with FoodRepository
    search after the Food Id in the Repository and checks if the Food can find
    search after a not existing Food Id in the Repository and checks if the Food is null
*/
    @Test
    public void findFoodById() {
        Optional<Food> foodOptional = foodRepository.findById(1L);
        Food food = foodOptional.get();
        assert (food.getId()==1L);
        Optional<Food> foodOptional2 = foodRepository.findById(10L);
        assertFalse (foodOptional2.isPresent());
    }

/*
    Test database connection with FoodRepository
    search after the Food Name in the Repository and checks if the Food can find
    search after a not existing Food Name in the Repository and checks if the Food is null
*/
    @Test
    public void findFoodByName(){
        //test query for food Apfel
        List<Food> foodList = foodRepository.findByName("pfe");
        Food foodOptional = foodList.get(0);
        assert (foodOptional.getId()==1L);
        List<Food> foodOptional2 = foodRepository.findByName("Software Engineering is cool :)");
        assertTrue (foodOptional2.isEmpty());
    }

/*
        Test database connection with NutritionalValuesRepository
        search after the NutritionalValues Id in the Repository and checks if the NutritionalValues can find
        search after a not existing NutritionalValues Id in the Repository and checks if the NutritionalValues is null
*/
    @Test
    public void findNutritionalValuesById() {
        Optional<NutritionalValues> nutritionalValuesOptional = nutritionalValuesRepository.findById(1L);
        NutritionalValues nutritionalValues = nutritionalValuesOptional.get();
        assert (nutritionalValues.getId()==1L);
        Optional<NutritionalValues> nutritionalValuesOptional2 = nutritionalValuesRepository.findById(10L);
        assertFalse (nutritionalValuesOptional2.isPresent());
    }

/*
    Test database connection with VitaminsRepository
    search after the Vitamins Id in the Repository and checks if the Vitamins can find
    search after a not existing Vitamins Id in the Repository and checks if the Vitamins is null
*/
    @Test
    public void findVitaminsById(){
        Optional<Vitamins> vitaminsOptional = vitaminsRepository.findById(1L);
        Vitamins vitamins = vitaminsOptional.get();
        assert (vitamins.getId()==1L);
        Optional<Vitamins> vitaminsOptional2 = vitaminsRepository.findById(10L);
        assertFalse (vitaminsOptional2.isPresent());
    }

    @Test
    public void findFoodEntryById() {
        Optional<FoodEntry> foodEntryOptional = foodEntryRepository.findById(1L);
        FoodEntry foodEntry = foodEntryOptional.get();
        assert (foodEntry.getId()==1L);
        Optional<Vitamins> vitaminsOptional2 = vitaminsRepository.findById(10L);
        assertFalse (vitaminsOptional2.isPresent());
    }


    @Test
    public void saveAndDeleteMeal() {
        Meal meal = new Meal(4L,new Date() , 40, MealCategory.BREAKFAST, 2.9, 3.0, 4.0, user, Collections.emptySet());
        Meal m = mealRepository.save(meal);
        Optional<Meal> mealOptional = mealRepository.findByIdAndUser(4L, user);
        assert  (mealOptional.isPresent());
        Meal meal2 = mealOptional.get();
        mealRepository.delete(meal2);
        Optional<Meal> mealOptional2 = mealRepository.findByIdAndUser(4L, user);
        assertFalse(mealOptional2.isPresent());
    }

    @Test
    public void saveAndDeleteFoodEntry() {
        Meal meal = mealRepository.findByIdAndUser(1L, user).get();
        Food food = foodRepository.findById(1L).get();
        FoodEntry foodEntry = new FoodEntry(3L, meal, food, 120, 240, 12.0, 16.0, 12.0);
        foodEntryRepository.save(foodEntry);
        Optional<FoodEntry> foodEntryOptional = foodEntryRepository.findById(3L);
        assert  (foodEntryOptional.isPresent());
        FoodEntry foodEntry1 = foodEntryOptional.get();
        foodEntryRepository.delete(foodEntry1);
        Optional<FoodEntry> foodEntryOptional1 = foodEntryRepository.findById(3L);
        assertFalse(foodEntryOptional1.isPresent());
    }

    @Test
    public void saveAndFindAllFoodValues() {
        Food food = new Food();
        food.setUnitSize(FoodUnitSize.GRAMS);
        food.setName("Test");
        Minerals minerals = new Minerals();
        Vitamins vitamins = new Vitamins();
        NutritionalValues nutritionalValues = new NutritionalValues();
        minerals.setChloride(9.0);
        minerals.setIron(10.0);
        minerals.setMagnesium(12.0);
        minerals.setPhosphorus(20.0);
        minerals.setSelenium(12.0);

        nutritionalValues.setCalories(1);
        nutritionalValues.setAlcohol(1.0);
        nutritionalValues.setCarbs(1.0);
        nutritionalValues.setFats(1.0);
        nutritionalValues.setFatsSaturated(1.0);
        nutritionalValues.setProteins(1.0);
        nutritionalValues.setSalt(1.0);
        nutritionalValues.setSugar(1.0);

        vitamins.setA(1);
        vitamins.setB1(2);
        vitamins.setB2(3);
        vitamins.setB6(4);
        vitamins.setB12(5);
        vitamins.setC(6);
        vitamins.setD(7);
        vitamins.setE(7);
        vitamins.setFol(8);
        vitamins.setBetacarotin(9);
        vitamins.setNiacin(0);
        vitamins.setRetinol(4);

        food.setNutritionalValues(nutritionalValues);
        food.setMinerals(minerals);
        food.setVitamins(vitamins);
        food.setFoodEntries(new HashSet<FoodEntry>());
        vitamins.setFood(food);
        minerals.setFood(food);
        nutritionalValues.setFood(food);
        foodRepository.save(food);
        vitaminsRepository.save(vitamins);
        mineralsRepository.save(minerals);
        nutritionalValuesRepository.save(nutritionalValues);

        Optional<Food> foodOptional = foodRepository.findById(4L);
        assert  (foodOptional.isPresent());
        Optional<Minerals> mineralsOptional = mineralsRepository.findById(4L);
        assert  (mineralsOptional.isPresent());
        Optional<Vitamins> vitaminsOptional = vitaminsRepository.findById(4L);
        assert  (vitaminsOptional.isPresent());
        Optional<NutritionalValues> nutritionalValuesOptional = nutritionalValuesRepository.findById(4L);
        assert  (nutritionalValuesOptional.isPresent());
    }
}

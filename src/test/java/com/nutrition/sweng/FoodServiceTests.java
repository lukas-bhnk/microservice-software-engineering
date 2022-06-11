package com.nutrition.sweng;

import com.nutrition.sweng.Exceptions.ResourceNotFoundException;
import com.nutrition.sweng.Model.*;
import com.nutrition.sweng.Repository.*;
import com.nutrition.sweng.Service.FoodService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class FoodServiceTests {

    @Mock
    private FoodRepository foodRepository;
    @Mock
    private MineralsRepository mineralsRepository;
    @Mock
    private VitaminsRepository vitaminsRepository;
    @Mock
    private NutritionalValuesRepository nutritionalValuesRepository;
    @Mock
    private UserRepository userRepository;

    private FoodService subject;
    private Food food;
    private Set<FoodEntry> foodList;
    private User user;

    private static final String TEST_FOODNAME = "Banane";

    @BeforeEach
    public void setUp() throws Exception {
        this.subject = new FoodService(this.foodRepository, this.nutritionalValuesRepository, this.vitaminsRepository, this.mineralsRepository);
        this.foodList = new HashSet<>();
        this.food = new Food();
        this.food.setFoodEntries(this.foodList);
        this.food = new Food();
        this.food.setName(TEST_FOODNAME);
        this.food.setUnitSize(FoodUnitSize.GRAMS);
        this.user = new User("test@email.com");
        this.user.setId(1L);
    }



    @Test
    public void shouldReturnFoodById() throws Exception {
        given(this.foodRepository.findById(1L)).willReturn(Optional.of(this.food));
        Food food = this.subject.getFood(1L);
        assertThat(food.getId(), is(this.food.getId()));
        assertThat(food.getFoodEntries(), is(this.food.getFoodEntries()));
    }



    @Test
    public void shouldReturnFoodByName() throws Exception {
        given(this.foodRepository.findByName(TEST_FOODNAME)).willReturn(List.of(this.food));
        List<Food> food = this.subject.getFood(TEST_FOODNAME);
        assertThat(food.get(0).getId(), is(this.food.getId()));
        assertThat(food.get(0).getFoodEntries(), is(this.food.getFoodEntries()));
    }


    @Test
    public void shouldTellIfFoodIsUnknown() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            this.subject.getFood("DIESES FOOD EXISTIERT NICHT");
        });
    }


    @Test
    public void shouldSaveFood() throws Exception {
        given(this.foodRepository.findById(1L)).willReturn(Optional.of(this.food));
        this.subject.saveAllFoodValues(this.food, new Minerals(), new Vitamins(), new NutritionalValues());
        Food food = this.subject.getFood(1L);
        assertThat(food.getId(), is(this.food.getId()));
        assertThat(food.getFoodEntries(), is(this.food.getFoodEntries()));
    }



}

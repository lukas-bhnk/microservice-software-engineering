package com.nutrition.sweng;

import com.nutrition.sweng.Model.*;
import com.nutrition.sweng.Repository.*;
import com.nutrition.sweng.Service.FoodInfoServiceClient;
import com.nutrition.sweng.Service.FoodService;
import com.nutrition.sweng.Service.NutritionalValuesService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.context.ActiveProfiles;

import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class NutritionalValuesServiceTests {


    @Mock
    private NutritionalValuesRepository nutritionalValuesRepository;

    private NutritionalValuesService subject;
    private Food food;
    private NutritionalValues nutritionalValues;
    private Set<FoodEntry> foodList;

    private static final String TEST_FOODNAME = "Banane";

    @BeforeEach
    public void setUp() throws Exception {
        this.subject = new NutritionalValuesService(nutritionalValuesRepository);
        this.foodList = new HashSet<>();
        this.food = new Food();
        this.food.setFoodEntries(this.foodList);
        this.food = new Food();
        this.food.setName(TEST_FOODNAME);
        this.food.setUnitSize(FoodUnitSize.GRAMS);
        this.nutritionalValues = new NutritionalValues(1L, 1.0, 2.0, 0, 0.0, 0.0, 0.0, 0.0, 0.0, food);
    }

    /*
    Tests if nutritionalValues find by id
     */
    @Test
    public void shouldReturnNutritionalValuesById() throws Exception {
        given(this.nutritionalValuesRepository.findById(1L)).willReturn(Optional.of(this.nutritionalValues));
        NutritionalValues nutritionalValues = this.subject.getNutritionalValues(1L);
        assertThat(nutritionalValues.getId(), is(this.nutritionalValues.getId()));
    }


    /*
    Tests exception when expected food is not in db
     */
    @Test
    public void shouldTellIfFoodIsUnknown() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            this.subject.getNutritionalValues(100L);
        });
    }






}

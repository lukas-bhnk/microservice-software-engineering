package com.nutrition.sweng;

import com.nutrition.sweng.Exceptions.ResourceNotFoundException;
import com.nutrition.sweng.Model.*;
import com.nutrition.sweng.Repository.*;
import com.nutrition.sweng.Service.MineralsService;
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
public class MineralsServiceTests {


    @Mock
    private MineralsRepository mineralsRepository;

    private MineralsService subject;
    private Food food;
    private Minerals minerals;
    private Set<FoodEntry> foodList;

    private static final String TEST_FOODNAME = "Banane";

    @BeforeEach
    public void setUp() throws Exception {
        this.subject = new MineralsService(mineralsRepository);
        this.foodList = new HashSet<>();
        this.food = new Food();
        this.food.setFoodEntries(this.foodList);
        this.food = new Food();
        this.food.setName(TEST_FOODNAME);
        this.food.setUnitSize(FoodUnitSize.GRAMS);
        this.minerals = new Minerals(1L, 1.0, 2.0, 0, 0.0, 0.0, 0.0, 0.0, 0.0, food);
    }

    /*
    Tests if minerals find by id
     */
    @Test
    public void shouldReturnMineralsById() throws Exception {
        given(this.mineralsRepository.findById(1L)).willReturn(Optional.of(this.minerals));
        Minerals minerals = this.subject.getMinerals(1L);
        assertThat(minerals.getId(), is(this.minerals.getId()));
    }


    /*
    Tests exception when expected food is not in db
     */
    @Test
    public void shouldTellIfFoodIsUnknown() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            this.subject.getMinerals(100L);
        });
    }






}

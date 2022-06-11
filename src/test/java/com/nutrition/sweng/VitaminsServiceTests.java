package com.nutrition.sweng;

import com.nutrition.sweng.Exceptions.ResourceNotFoundException;
import com.nutrition.sweng.Model.*;
import com.nutrition.sweng.Repository.VitaminsRepository;
import com.nutrition.sweng.Service.VitaminsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class VitaminsServiceTests {


    @Mock
    private VitaminsRepository vitaminsRepository;

    private VitaminsService subject;
    private Food food;
    private Vitamins vitamins;
    private Set<FoodEntry> foodList;

    private static final String TEST_FOODNAME = "Banane";

    @BeforeEach
    public void setUp() throws Exception {
        this.subject = new VitaminsService(vitaminsRepository);
        this.foodList = new HashSet<>();
        this.food = new Food();
        this.food.setFoodEntries(this.foodList);
        this.food = new Food();
        this.food.setName(TEST_FOODNAME);
        this.food.setUnitSize(FoodUnitSize.GRAMS);
        this.vitamins = new Vitamins(1L,0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, food);
    }

    /*
    Tests if vitamins find by id
     */
    @Test
    public void shouldReturnVitaminsById() throws Exception {
        given(this.vitaminsRepository.findById(1L)).willReturn(Optional.of(this.vitamins));
        Vitamins vitamins = this.subject.getVitamins(1L);
        assertThat(vitamins.getId(), is(this.vitamins.getId()));
    }


    /*
    Tests exception when expected food is not in db
     */
    @Test
    public void shouldTellIfFoodIsUnknown() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            this.subject.getVitamins(100L);
        });
    }






}

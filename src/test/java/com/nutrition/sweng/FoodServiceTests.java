package com.nutrition.sweng;

import com.nutrition.sweng.Model.*;
import com.nutrition.sweng.Repository.*;
import com.nutrition.sweng.Service.FoodInfoServiceClient;
import com.nutrition.sweng.Service.FoodService;
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
    private FoodInfoServiceClient foodInfoServiceClient;
    @Mock
    private UserRepository userRepository;

    private FoodService subject;
    private Food food;
    private Set<FoodEntry> foodList;
    private User user;

    private static final String TEST_USERNAME = "harry@hacker.de";
    private static final String TEST_PRICE = "1,99";
    private static final String TEST_FOODNAME = "Banane";

    @BeforeEach
    public void setUp() throws Exception {
        this.subject = new FoodService(this.foodRepository, this.nutritionalValuesRepository, this.vitaminsRepository, this.mineralsRepository,this.foodInfoServiceClient);
        this.foodList = new HashSet<>();
        this.food = new Food();
        this.food.setFoodEntries(this.foodList);
        this.food = new Food();
        this.food.setName(TEST_FOODNAME);
        this.food.setUnitSize(FoodUnitSize.GRAMS);
        this.user = new User("test@email.com");
        this.user.setId(1L);
    }

    /*
    Tests if food find by id
     */
    @Test
    public void shouldReturnFoodById() throws Exception {
        given(this.foodRepository.findById(1L)).willReturn(Optional.of(this.food));
        Food food = this.subject.getFood(1L);
        assertThat(food.getId(), is(this.food.getId()));
        assertThat(food.getFoodEntries(), is(this.food.getFoodEntries()));
    }

    /*
    Tests if food find by name
    */
    @Test
    public void shouldReturnFoodByName() throws Exception {
        given(this.foodRepository.findByName(TEST_FOODNAME)).willReturn(List.of(this.food));
        List<Food> food = this.subject.getFood(TEST_FOODNAME);
        assertThat(food.get(0).getId(), is(this.food.getId()));
        assertThat(food.get(0).getFoodEntries(), is(this.food.getFoodEntries()));
    }

    /*
    Tests exception when expected food is not in db
     */
    @Test
    public void shouldTellIfFoodIsUnknown() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            this.subject.getFood("DIESES FOOD EXISTIERT NICHT");
        });
    }


    /*
    Tests if food can be saved
     */
    @Test
    public void shouldSaveFood() throws Exception {
        given(this.foodRepository.findById(1L)).willReturn(Optional.of(this.food));
        this.subject.saveAllFoodValues(this.food, new Minerals(), new Vitamins(), new NutritionalValues());
        Food food = this.subject.getFood(1L);
        assertThat(food.getId(), is(this.food.getId()));
        assertThat(food.getFoodEntries(), is(this.food.getFoodEntries()));
    }


    /*
    Should can add all Food Values
     */
    @Test
    public void shouldCanGetAllFoodInfos() throws Exception {
        given(this.foodInfoServiceClient.getMinerals("1")).willReturn(new Minerals(1L,0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, food));
        given(this.foodInfoServiceClient.getFood("1")).willReturn(this.food);
        given(this.foodInfoServiceClient.getNutritionalValues("1")).willReturn(new NutritionalValues(1L, 1.0, 2.0, 0, 0.0, 0.0, 0.0, 0.0, 0.0, food));
        given(this.foodInfoServiceClient.getVitamins("1")).willReturn(new Vitamins(1L,0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, food));
        String s = this.subject.getAllFoodInfos(1L);
        assertThat(s,is("Food{id=0, name='Banane', unitSize='GRAMS'}\r\nMinerals{id=1, chloride='0.0', magnesium='0.0', phosphorus='0.0', iron='0.0', potassium='0.0', selenium='0.0', sodium='0.0', zinc='0.0'}\r\nNutritionalValues{id=1, carbs='1.0', proteins='2.0', fats='0.0', fatsSaturated='0.0', alcohol='0.0', salt='0.0'}\r\nVitamins{id=1, c='0.0', fol='0.0', a='0.0', b1='0.0', b2='0.0', b11='0.0', b12='0.0', d='0.0', e='0.0', k='0.0', betacarotin='0.0', niacin='0.0', retinol='0.0'}"));

    }


}

package com.nutrition.sweng.Controller;

import com.nutrition.sweng.DTO.FoodDto;
import com.nutrition.sweng.DTO.MealDto;
import com.nutrition.sweng.Model.Food;
import com.nutrition.sweng.Model.Meal;
import com.nutrition.sweng.Model.MealCategory;
import com.nutrition.sweng.Service.MealService;
import com.nutrition.sweng.security.JwtValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("rest/meal")
public class MealController {

    private MealService mealService;
    private JwtValidator jwtValidator;
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    public MealController(MealService mealService, JwtValidator jwtValidator){
        this.mealService = mealService;
        this.jwtValidator = jwtValidator;
    }

    /**
     * Get a specific meal by its id.
     * @param id of the meal
     * @return meal with the param id
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('NORMAL') || hasAuthority('PREMIUM') || hasAuthority('ADMIN')")
    public MealDto getMeal(@PathVariable Long id, @RequestHeader String Authorization){
        LOG.info("Received GET-Request /rest/meal/{}", id);
        String email = jwtValidator.getUserEmail(Authorization.substring(7));
        Meal meal = this.mealService.getMeal(id, email);
        return new MealDto(meal);
    }
    /**
     * Get all meals, that are saved for a specific day.
     * @param day
     * @param month
     * @param year
     * @return mealList, with all meals of the date
     */
    @GetMapping("/{day}/{month}/{year}")
    @PreAuthorize("hasAuthority('NORMAL') || hasAuthority('PREMIUM') || hasAuthority('ADMIN')")
    public List<MealDto > getMeal(@PathVariable int day, @PathVariable int month, @PathVariable int year, @RequestHeader String Authorization) throws ParseException {
        LOG.info("Received GET-Request /rest/meal/{day}/{month}/{year}).", day, month, year);
        String email = jwtValidator.getUserEmail(Authorization.substring(7));
        List<Meal> meals = this.mealService.getDailyMeals(new SimpleDateFormat("yyyy-MM-dd").parse(year+"-"+month+"-"+day), email);
        List<MealDto> mealDtos = new ArrayList<MealDto>();
        for (Meal m: meals) mealDtos.add(new MealDto(m));
        return mealDtos;
    }

    /**
     * Update the existing Foodentry quantity  for a specif meal.
     * The quantity will be multiply with the nutritional values per 100grams/millitres
     * @param mealId
     * @param foodId
     * @param quantity
     * @return updatedMeal
     */
    @PatchMapping("/{mealId}/{foodId}/{quantity}")
    @PreAuthorize("hasAuthority('NORMAL') || hasAuthority('PREMIUM') || hasAuthority('ADMIN')")
    public MealDto updateFoodQuantity(@PathVariable Long mealId, @PathVariable Long foodId, @PathVariable Integer quantity, @RequestHeader String Authorization){
        LOG.info("Received GET-Request /rest/meal/{mealId}/{foodId}/{quantity}).", mealId, foodId, quantity);
        String email = jwtValidator.getUserEmail(Authorization.substring(7));
        Meal meal = this.mealService.updateQuantity(mealId, foodId, quantity, email);
        return new MealDto(meal);
    }

    /**
     * Add a Food with for a specif meal.
     * The calories will be multiply with the nutritional values per 100grams/millitres
     * @param mealId
     * @param foodId
     * @param quantity
     * @return updatedMeal
     */
    @PostMapping("/{mealId}/{foodId}/{quantity}")
    @PreAuthorize("hasAuthority('NORMAL') || hasAuthority('PREMIUM') || hasAuthority('ADMIN')")
    public MealDto addFood(@PathVariable Long mealId, @PathVariable Long foodId, @PathVariable Integer quantity, @RequestHeader String Authorization){
        LOG.info("Received POST-Request /rest/meal/{mealId}/{foodId}/{quantity}).", mealId, foodId, quantity);
        String email = jwtValidator.getUserEmail(Authorization.substring(7));
        Meal meal = this.mealService.addFood(mealId, foodId, quantity, email);
        return new MealDto(meal);
    }

    /**
     * Create a Meal for a specif day, Mealcategory and user
     * @param day
     * @param month
     * @param year
     * @param category (breakfast, lunch, dinner, snack)
     * @return created food
     */
    @PostMapping("/{day}/{month}/{year}/{category}")
    @PreAuthorize("hasAuthority('NORMAL') || hasAuthority('PREMIUM') || hasAuthority('ADMIN')")
    public MealDto createMeal(@PathVariable int day, @PathVariable int month, @PathVariable int year, @PathVariable String category, @RequestHeader String Authorization) throws ParseException {
        LOG.info("Received POST-Request /rest/meal/{day}/{month}/{year}).", day, month, year);
        String email = jwtValidator.getUserEmail(Authorization.substring(7));
        MealCategory mealCategory;
        if(category.equals("breakfast")) {mealCategory = MealCategory.BREAKFAST;}
        else {if (category.equals("lunch")){mealCategory = MealCategory.LUNCH;}
        else {if (category.equals("dinner")) {mealCategory = MealCategory.DINNER;}
        else {if (category.equals("snack")) {mealCategory = MealCategory.SNACK;}
        else throw new IllegalArgumentException("Can not resolve Category");}}}
        Meal meal = this.mealService.createMeal(new SimpleDateFormat("yyyy-MM-dd").parse(year+"-"+month+"-"+day), mealCategory, email);
        return new MealDto(meal);
    }

    /**
     * Delete a food by its it from the meal
     * @param mealId
     * @param foodId
     * @return changed meal
     */
    @DeleteMapping("/{mealId}/{foodId}")
    @PreAuthorize("hasAuthority('NORMAL') || hasAuthority('PREMIUM') || hasAuthority('ADMIN')")
    public MealDto deleteFood(@PathVariable Long mealId, @PathVariable Long foodId, @RequestHeader String Authorization){
        LOG.info("Received DELETE-Request /rest/meal/{mealId}/{foodId}).", mealId, foodId);
        String email = jwtValidator.getUserEmail(Authorization.substring(7));
        Meal meal = this.mealService.deleteFood(mealId, foodId, email);
        return new MealDto(meal);
    }
}

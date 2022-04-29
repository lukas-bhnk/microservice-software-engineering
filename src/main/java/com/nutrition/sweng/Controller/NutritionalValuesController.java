package com.nutrition.sweng.Controller;

import com.nutrition.sweng.DTO.NutritionalValuesDto;
import com.nutrition.sweng.Model.NutritionalValues;
import com.nutrition.sweng.Service.NutritionalValuesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rest/nutritionalValues")
public class NutritionalValuesController {
    private NutritionalValuesService nutritionalValuesService;

    @Autowired
    public NutritionalValuesController(NutritionalValuesService nutritionalValuesService){
        this.nutritionalValuesService = nutritionalValuesService;
    }

    @GetMapping("/{id}")
    public NutritionalValuesDto getNutritionalValues(@PathVariable Long id){
        NutritionalValues nutritionalValues = this.nutritionalValuesService.getNutritionalValues(id);
        return new NutritionalValuesDto(nutritionalValues);
    }
}
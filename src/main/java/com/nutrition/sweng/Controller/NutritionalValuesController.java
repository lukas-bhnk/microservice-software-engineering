package com.nutrition.sweng.Controller;

import com.nutrition.sweng.DTO.NutritionalValuesDto;
import com.nutrition.sweng.Model.NutritionalValues;
import com.nutrition.sweng.Service.NutritionalValuesService;
import com.nutrition.sweng.security.JwtValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rest/nutritionalValues")
public class NutritionalValuesController {
    private NutritionalValuesService nutritionalValuesService;
    private final Logger LOG = LoggerFactory.getLogger(getClass());
    private JwtValidator jwtValidator;

    @Autowired
    public NutritionalValuesController(NutritionalValuesService nutritionalValuesService, JwtValidator jwtValidator){
        this.nutritionalValuesService = nutritionalValuesService;
        this.jwtValidator = jwtValidator;
    }

    /**
     * Get the NutritionalValues of a specific food by its id
     * @param id of the food
     * @return nutritionalValues of the food
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('NORMAL') || hasAuthority('PREMIUM') || hasAuthority('ADMIN')")
    public NutritionalValuesDto getNutritionalValues(@PathVariable Long id){
        LOG.info("Received GET-Request /rest/nutritionalValues/{id}).", id);
        NutritionalValues nutritionalValues = this.nutritionalValuesService.getNutritionalValues(id);
        return new NutritionalValuesDto(nutritionalValues);
    }
}
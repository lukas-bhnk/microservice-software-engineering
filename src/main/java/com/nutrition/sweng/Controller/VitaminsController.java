package com.nutrition.sweng.Controller;

import com.nutrition.sweng.DTO.VitaminsDto;
import com.nutrition.sweng.Model.Vitamins;
import com.nutrition.sweng.Service.VitaminsService;
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
@RequestMapping("rest/vitamins")
public class VitaminsController {
    private VitaminsService vitaminsService;
    private JwtValidator jwtValidator;
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    public VitaminsController(VitaminsService vitaminsService, JwtValidator jwtValidator){
        this.vitaminsService = vitaminsService;
        this.jwtValidator = jwtValidator;
    }

    /**
     * Get the Vitamins of a specific food by its id
     * @param id of the food
     * @return vitamins of the food
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('NORMAL') || hasAuthority('PREMIUM') || hasAuthority('ADMIN')")
    public VitaminsDto getVitamins(@PathVariable Long id){
        LOG.info("Received GET-Request /rest/vitamins/{id}).", id);
        Vitamins vitamins = this.vitaminsService.getVitamins(id);
        return new VitaminsDto(vitamins);
    }
}

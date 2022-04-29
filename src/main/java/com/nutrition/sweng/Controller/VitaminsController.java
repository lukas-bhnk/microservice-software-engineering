package com.nutrition.sweng.Controller;

import com.nutrition.sweng.DTO.VitaminsDto;
import com.nutrition.sweng.Model.Vitamins;
import com.nutrition.sweng.Service.VitaminsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rest/vitamins")
public class VitaminsController {
    private VitaminsService vitaminsService;

    @Autowired
    public VitaminsController(VitaminsService vitaminsService){
        this.vitaminsService = vitaminsService;
    }

    @GetMapping("/{id}")
    public VitaminsDto getVitamins(@PathVariable Long id){
        Vitamins vitamins = this.vitaminsService.getVitamins(id);
        return new VitaminsDto(vitamins);
    }
}

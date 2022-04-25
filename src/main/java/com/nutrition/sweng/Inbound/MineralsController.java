package com.nutrition.sweng.Inbound;

import com.nutrition.sweng.Model.Minerals;
import com.nutrition.sweng.Service.MineralsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rest/minerals")
public class MineralsController {
    private MineralsService mineralsService;

    @Autowired
    public MineralsController(MineralsService mineralsService){
        this.mineralsService = mineralsService;
    }

    @GetMapping("/{id}")
    public MineralsDto getMinerals(@PathVariable Long id){
        Minerals minerals = this.mineralsService.getMinerals(id);
        return new MineralsDto(minerals);
    }
}

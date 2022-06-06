package com.nutrition.sweng.Controller;

import com.nutrition.sweng.DTO.MineralsDto;
import com.nutrition.sweng.Model.Minerals;
import com.nutrition.sweng.Service.MineralsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * This is
 */
@RestController
@RequestMapping("rest/minerals")
public class MineralsController {
    private MineralsService mineralsService;
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    public MineralsController(MineralsService mineralsService){
        this.mineralsService = mineralsService;
    }

    /**
     * Get the Minerals of a specific food by its id
     * @param id of the food
     * @return minerals of the food
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('NORMAL') || hasAuthority('PREMIUM') || hasAuthority('ADMIN')")
    public MineralsDto getMinerals(@PathVariable Long id){
        LOG.info("Received GET-Request /rest/minerals/{id}).", id);
        Minerals minerals = this.mineralsService.getMinerals(id);
        return new MineralsDto(minerals);
    }
}

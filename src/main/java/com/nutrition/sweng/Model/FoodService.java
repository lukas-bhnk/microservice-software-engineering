package com.nutrition.sweng.Model;

import com.nutrition.sweng.Outbound.FoodJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodService {
    private FoodRepository foodRepository;

    @Autowired
    public FoodService(FoodRepository foodRepo) {
         this.foodRepository = foodRepo;
    }

}

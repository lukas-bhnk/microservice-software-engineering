package com.nutrition.sweng.Service;

import com.nutrition.sweng.Model.Food;
import com.nutrition.sweng.Model.Minerals;
import com.nutrition.sweng.Model.NutritionalValues;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(url = "${foodInfo.service.url}", name = "FoodInfoService")
public interface FoodInfoServiceClient {

    @RequestMapping(method = RequestMethod.GET, value = "/minerals/?id={id}")
    public String getMineralsValues(@PathVariable("id") String id);

    @RequestMapping(method = RequestMethod.GET, value = "/nutritionalValues/?id={id}")
    public String getNutritionalValues(@PathVariable("id") String id);

    @RequestMapping(method = RequestMethod.GET, value = "/vitamins/?id={id}")
    public String getVitamins(@PathVariable("id") String id);

    @RequestMapping(method = RequestMethod.GET, value = "/food/?id={id}")
    public String getFood(@PathVariable("id") String id);


}

package com.nutrition.sweng.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NutritionalValuesService {
    private NutritionalValuesRepository nutritionalValuesRepository;

    @Autowired
    public NutritionalValuesService(NutritionalValuesRepository nutritionalValuesRepository){
        this.nutritionalValuesRepository = nutritionalValuesRepository;
    }

    public NutritionalValues getNutritionalValues(long id){
        Optional<NutritionalValues> nutritionalValuesOptional = nutritionalValuesRepository.findById(id);
        if(nutritionalValuesOptional.isPresent()){
            NutritionalValues nutritionalValues = nutritionalValuesOptional.get();
            return nutritionalValues;
        }
        else{
            throw new ResourceNotFoundException("These nutritionalValues are not in the Database");
        }
    }
}

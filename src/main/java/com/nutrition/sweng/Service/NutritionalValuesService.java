package com.nutrition.sweng.Service;

import com.nutrition.sweng.Model.NutritionalValues;
import com.nutrition.sweng.Exceptions.ResourceNotFoundException;
import com.nutrition.sweng.Repository.NutritionalValuesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class NutritionalValuesService {
    private NutritionalValuesRepository nutritionalValuesRepository;
    private final Logger LOG =  LoggerFactory.getLogger(getClass());

    @Autowired
    public NutritionalValuesService(NutritionalValuesRepository nutritionalValuesRepository){
        this.nutritionalValuesRepository = nutritionalValuesRepository;
    }
    @Transactional(readOnly=true)
    public NutritionalValues getNutritionalValues(long id){
        Optional<NutritionalValues> nutritionalValuesOptional = nutritionalValuesRepository.findById(id);
        if(nutritionalValuesOptional.isPresent()){
            NutritionalValues nutritionalValues = nutritionalValuesOptional.get();
            return nutritionalValues;
        }
        else{
            LOG.error("No Minerals for this id was found. NutritionalValues are not in DB");
            throw new ResourceNotFoundException("These nutritionalValues are not in the Database");
        }
    }
}

package com.nutrition.sweng.Service;

import com.nutrition.sweng.Model.ResourceNotFoundException;
import com.nutrition.sweng.Model.Vitamins;
import com.nutrition.sweng.Repository.VitaminsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class VitaminsService {
    private VitaminsRepository vitaminsRepository;
    private final Logger LOG =  LoggerFactory.getLogger(getClass());

    @Autowired
    public VitaminsService(VitaminsRepository vitaminsRepository){
        this.vitaminsRepository = vitaminsRepository;
    }

    public Vitamins getVitamins(long id){
        Optional<Vitamins> vitaminsOptional = vitaminsRepository.findById(id);
        if(vitaminsOptional.isPresent()){
            Vitamins vitamins = vitaminsOptional.get();
            return vitamins;
        }
        else{
            LOG.error("No Vitamins for this id was found. Vitamins are not in DB");
            throw new ResourceNotFoundException("These vitamins are not in the Database");
        }
    }
}

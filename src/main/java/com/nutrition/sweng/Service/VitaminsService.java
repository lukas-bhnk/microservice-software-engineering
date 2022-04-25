package com.nutrition.sweng.Service;

import com.nutrition.sweng.Model.ResourceNotFoundException;
import com.nutrition.sweng.Model.Vitamins;
import com.nutrition.sweng.Repository.VitaminsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VitaminsService {
    private VitaminsRepository vitaminsRepository;

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
            throw new ResourceNotFoundException("These vitamins are not in the Database");
        }
    }
}

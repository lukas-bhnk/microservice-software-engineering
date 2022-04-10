package com.nutrition.sweng.Model;

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

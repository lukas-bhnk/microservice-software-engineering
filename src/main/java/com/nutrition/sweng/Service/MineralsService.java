package com.nutrition.sweng.Service;

import com.nutrition.sweng.Model.Minerals;
import com.nutrition.sweng.Model.MineralsRepository;
import com.nutrition.sweng.Model.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MineralsService {
    private MineralsRepository mineralsRepository;

    @Autowired
    public MineralsService(MineralsRepository mineralsRepository){
        this.mineralsRepository = mineralsRepository;
    }

    public Minerals getMinerals(long id){
        Optional<Minerals> mineralsOptional = mineralsRepository.findById(id);
        if(mineralsOptional.isPresent()){
            Minerals minerals = mineralsOptional.get();
            return minerals;
        }
        else{
            throw new ResourceNotFoundException("These minerals are not in the Database");
        }
    }
}

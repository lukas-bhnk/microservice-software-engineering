package com.nutrition.sweng.Service;

import com.nutrition.sweng.Model.Minerals;
import com.nutrition.sweng.Repository.MineralsRepository;
import com.nutrition.sweng.Exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class MineralsService {
    private MineralsRepository mineralsRepository;
    private final Logger LOG =  LoggerFactory.getLogger(getClass());

    @Autowired
    public MineralsService(MineralsRepository mineralsRepository){
        this.mineralsRepository = mineralsRepository;
    }

    @Transactional(readOnly=true)
    public Minerals getMinerals(long id){
        LOG.info("Execute getMinerals with id {}", id);
        Optional<Minerals> mineralsOptional = mineralsRepository.findById(id);
        if(mineralsOptional.isPresent()){
            Minerals minerals = mineralsOptional.get();
            return minerals;
        }
        else{
            LOG.error("No Minerals for this id was found. Minerals are not in DB");
            throw new ResourceNotFoundException("These minerals are not in the Database");
        }
    }
}

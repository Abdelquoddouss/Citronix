package com.java.citronix.service.Impl;

import com.java.citronix.domaine.entities.Champ;
import com.java.citronix.repository.ChampRepository;
import com.java.citronix.service.ChampService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChampServiceImpl implements ChampService {

    @Autowired
    private ChampRepository champRepository;


    @Override
    public Champ save( Champ champ) {
        if (champ == null) {
            throw new RuntimeException("Champ is null");
        }
        return champRepository.save(champ);
    }




}

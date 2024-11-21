package com.java.citronix.service.Impl;

import com.java.citronix.domaine.entities.Recolte;
import com.java.citronix.repository.RecolteRepository;
import com.java.citronix.service.RecolteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecolteServiceImpl implements RecolteService {

    @Autowired
    private RecolteRepository recolteRepository;

    @Override
    public Recolte createRecolte(Recolte recolte) {
        return recolteRepository.save(recolte);
    }

}

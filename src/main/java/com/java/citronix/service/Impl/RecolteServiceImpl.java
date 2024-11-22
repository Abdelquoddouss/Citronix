package com.java.citronix.service.Impl;

import com.java.citronix.domaine.entities.Recolte;
import com.java.citronix.exception.ResourceNotFoundException;
import com.java.citronix.repository.RecolteRepository;
import com.java.citronix.service.RecolteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RecolteServiceImpl implements RecolteService {

    @Autowired
    private RecolteRepository recolteRepository;

    @Override
    public Recolte createRecolte(Recolte recolte) {


        recolte.getDetailsRecolte().forEach(hd ->{
            hd.setRecolte(recolte);
            hd.setQuantite(hd.getArbre().getProductivite());
        });
        recolte.setQuantiteTotal(recolte.
                getDetailsRecolte().stream().
                mapToDouble(d -> d.getQuantite()).sum());



        return recolteRepository.save(recolte);
    }

    @Override
    public Recolte getRecolteById(UUID recolteId) {
        return recolteRepository.findById(recolteId)
                .orElseThrow(() -> new ResourceNotFoundException("Recolte not found with ID: " + recolteId));
    }

    @Override
    public List<Recolte> getAllRecoltes() {
        return recolteRepository.findAll();
    }

    @Override
    public void deleteRecolte(UUID recolteId) {
        Recolte recolte = recolteRepository.findById(recolteId)
                .orElseThrow(() -> new ResourceNotFoundException("Recolte not found with ID: " + recolteId));
        recolteRepository.delete(recolte);
    }
}

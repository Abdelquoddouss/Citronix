package com.java.citronix.repository.impl;

import com.java.citronix.domaine.entities.Ferme;
import com.java.citronix.repository.FermeSearchRepository;
import com.java.citronix.web.vm.FermeSearchResultVm;
import com.java.citronix.web.vm.FermeSearchVm;
import com.java.citronix.web.vm.FermeVm;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FermeSearchRepositoryImpl implements FermeSearchRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<FermeSearchResultVm> findByCriteria(FermeSearchVm search) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<FermeSearchResultVm> query = cb.createQuery(FermeSearchResultVm.class);
        Root<Ferme> farmRoot = query.from(Ferme.class);

        // Projection into FermeSearchResultVm
        query.select(cb.construct(FermeSearchResultVm.class,
                farmRoot.get("nom"),
                farmRoot.get("localisation"),
                farmRoot.get("dateCreation")
        ));

        List<Predicate> predicates = new ArrayList<>();

        // Add dynamic criteria
        if (StringUtils.hasText(search.getNom())) {
            predicates.add(cb.equal(farmRoot.get("nom"), search.getNom()));
        }
        if (StringUtils.hasText(search.getLocalisation())) {
            predicates.add(cb.like(cb.lower(farmRoot.get("localisation")),
                    "%" + search.getLocalisation().toLowerCase() + "%"));
        }
        if (search.getDateCreation() != null) {
            predicates.add(cb.equal(farmRoot.get("dateCreation"), search.getDateCreation()));
        }

        // Apply predicates to query
        if (!predicates.isEmpty()) {
            query.where(cb.and(predicates.toArray(new Predicate[0])));
        }

        return entityManager.createQuery(query).getResultList();
    }


}

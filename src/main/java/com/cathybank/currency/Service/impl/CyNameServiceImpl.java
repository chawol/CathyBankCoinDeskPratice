package com.cathybank.currency.Service.impl;

import com.cathybank.currency.Enity.CyName;
import com.cathybank.currency.Repo.CyNameRepo;
import com.cathybank.currency.Service.CyNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component
public class CyNameServiceImpl implements CyNameService {
    @Autowired
    private CyNameRepo cyNameRepo;

    public CyNameRepo getDeskRepo() {
        return cyNameRepo;
    }

    @Autowired
    private EntityManager em;

    private CriteriaQuery<CyName> toPredicate(CyName qc) {
        CriteriaBuilder cb = em.getCriteriaBuilder(
        );
        CriteriaQuery<CyName> cq = cb.createQuery(CyName.class);
        Root<CyName> cyNameRoot = cq.from(CyName.class);
        List<Predicate> predicates = new ArrayList<>();
        if (qc.getId() != 0) {
            predicates.add(cb.equal(cyNameRoot.get("id"), qc.getId()));
        }
        if (qc.getCyChtName() != null) {
            predicates.add(cb.like(cb.lower(cyNameRoot.get("cyChtName")), "%" + qc.getCyChtName().toLowerCase(Locale.ROOT) + "%"));
        }
        if (qc.getCyName() != null) {
            predicates.add(cb.equal(cyNameRoot.get("cyName"), qc.getCyName()));
        }
        cq.where(predicates.toArray(new Predicate[0]));
        return cq;
    }

    @Override
    public void delete(int id) {
        cyNameRepo.deleteById(id);
    }


    @Override
    public CyName add(CyName cyName) {
        return cyNameRepo.save(cyName);
    }

    @Override
    public List<CyName> find(CyName cyName) {
        return em.createQuery(toPredicate(cyName)).getResultList();
    }

    @Override
    public CyName update( CyName cyName) {
        return cyNameRepo.save(cyName);
    }

    @Override
    public List<CyName> findAll() {
        return cyNameRepo.findAll();
    }

    @Override
    public String getChtNameByCode(String code) {
        return cyNameRepo.findCyChtNameByCode(code);
    }
}

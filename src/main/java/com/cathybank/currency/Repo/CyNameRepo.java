package com.cathybank.currency.Repo;

import com.cathybank.currency.Enity.CyName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CyNameRepo extends JpaRepository<CyName, Integer> {
    @Query(value = "select  po.cyChtName from CyName po where po.cyName = ?1")
    String findCyChtNameByCode(String code);

}

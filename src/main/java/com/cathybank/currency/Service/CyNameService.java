package com.cathybank.currency.Service;

import com.cathybank.currency.Enity.CyName;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CyNameService {
    List<CyName> findAll();

    CyName add(CyName cyName);

    void delete(int id);

    List<CyName> find(CyName cyName);

    CyName update( CyName cyName);

    String getChtNameByCode(String code);
}

package com.cathybank.currency.Service;

import com.cathybank.currency.PO.CoinDeskPo;

import com.cathybank.currency.VO.CurrencyInfoVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
public interface CoinDeskService {

    CoinDeskPo getCoinDeskInfo() throws JsonProcessingException;

    String getCoinDeskInfoRaw() ;

    CurrencyInfoVo getCurrencyInfo() throws JsonProcessingException, ParseException;
}

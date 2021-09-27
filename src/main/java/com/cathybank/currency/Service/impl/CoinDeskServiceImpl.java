package com.cathybank.currency.Service.impl;

import com.cathybank.currency.PO.BpiPo;
import com.cathybank.currency.PO.CoinDeskPo;
import com.cathybank.currency.PO.CurrencyInfo;
import com.cathybank.currency.Service.CoinDeskService;
import com.cathybank.currency.Service.CyNameService;
import com.cathybank.currency.VO.CurrencyInfoVo;
import com.cathybank.currency.dto.CurrencyDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Component
public class CoinDeskServiceImpl implements CoinDeskService {

    @Autowired
    CyNameService cyNameService;

    private final RestTemplate restTemplate = new RestTemplate();
    ObjectMapper mapper = new ObjectMapper();

    @Override
    public CoinDeskPo getCoinDeskInfo() throws JsonProcessingException {
        ResponseEntity<String> responseEntity =
                restTemplate.getForEntity("https://api.coindesk.com/v1/bpi/currentprice.json", String.class);
        return mapper.readValue(responseEntity.getBody(), CoinDeskPo.class);
    }

    @Override
    public String getCoinDeskInfoRaw() {
        ResponseEntity<String> responseEntity =
                restTemplate.getForEntity("https://api.coindesk.com/v1/bpi/currentprice.json", String.class);
        return responseEntity.getBody();
    }

    @Override
    public CurrencyInfoVo getCurrencyInfo() throws JsonProcessingException, ParseException {
        CoinDeskPo coinDesk = getCoinDeskInfo();
        CurrencyInfoVo currencyInfoVo = new CurrencyInfoVo();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC+0"));
        Date temp = sdf.parse(coinDesk.getTime().getUpdatedISO());
        SimpleDateFormat sdfTai = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC+8"));
        currencyInfoVo.setUpdateTime(sdfTai.format(temp));
        currencyInfoVo.setBpi(convetToDto(coinDesk.getBpi()));
        return currencyInfoVo;

    }

    private List<CurrencyDto> convetToDto(BpiPo bpiPo) {
        List<CurrencyDto> dtos = new ArrayList<>();
        CurrencyInfo usd = bpiPo.getUsd();
        CurrencyInfo gbp = bpiPo.getGbp();
        CurrencyInfo eur = bpiPo.getEur();

        dtos.add(new CurrencyDto(usd.getCode(), cyNameService.getChtNameByCode(usd.getCode()), usd.getRate()));
        dtos.add(new CurrencyDto(gbp.getCode(), cyNameService.getChtNameByCode(gbp.getCode()), gbp.getRate()));
        dtos.add(new CurrencyDto(eur.getCode(), cyNameService.getChtNameByCode(eur.getCode()), eur.getRate()));
        return dtos;
    }
}

package com.cathybank.currency.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CurrencyDto {
    private String code;
    private String currencyChtName;
    private String rate;

    public CurrencyDto(String code, String currencyChtName, String rate) {
        this.code = code;
        this.currencyChtName = currencyChtName;
        this.rate = rate;
    }
}

package com.cathybank.currency.PO;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CurrencyInfo {
    private String code;
    private String symbol;
    private String rate;
    private String description;
    private String rate_float;
}

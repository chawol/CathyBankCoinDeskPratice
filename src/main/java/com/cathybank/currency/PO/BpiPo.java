package com.cathybank.currency.PO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BpiPo {
    @JsonProperty("USD")
    private CurrencyInfo usd;
    @JsonProperty("GBP")
    private CurrencyInfo gbp;
    @JsonProperty("EUR")
    private CurrencyInfo eur;

}

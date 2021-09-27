package com.cathybank.currency.PO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CoinDeskPo {
    private TimePo time;
    private String disclaimer;
    private String chartName;
    private BpiPo bpi;
}

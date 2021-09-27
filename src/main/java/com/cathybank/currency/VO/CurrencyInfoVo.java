package com.cathybank.currency.VO;

import com.cathybank.currency.dto.CurrencyDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CurrencyInfoVo {

    private String updateTime;
    private List<CurrencyDto> bpi;

}

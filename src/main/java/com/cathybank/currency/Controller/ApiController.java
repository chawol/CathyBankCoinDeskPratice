package com.cathybank.currency.Controller;

import com.cathybank.currency.Enity.CyName;
import com.cathybank.currency.Service.CoinDeskService;
import com.cathybank.currency.Service.CyNameService;
import com.cathybank.currency.VO.CurrencyInfoVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/currencyName")
public class ApiController {

    final CyNameService cyNameService;
    final CoinDeskService coinDeskService;


    public ApiController(CyNameService cyNameService, CoinDeskService coinDeskService) {
        this.cyNameService = cyNameService;
        this.coinDeskService = coinDeskService;
    }

    @GetMapping
    public List<CyName> test() {
        return cyNameService.findAll();
    }

    @PostMapping("add")
    public CyName add(@RequestBody CyName cyName) {
        return cyNameService.add(cyName);
    }

    @DeleteMapping
    public String delete(@RequestParam(required = true) Integer id) throws IllegalAccessError {
        try {
            cyNameService.delete(id);
        } catch (Exception e) {
            return "False";
        }
        return "Sucess";
    }

    @PostMapping("find")
    public List<CyName> find(@RequestBody CyName cyName) {
        return cyNameService.find(cyName);
    }

    @PutMapping("/update")
    public CyName update(@RequestBody CyName cyName) {
        return cyNameService.update(cyName);
    }

    @GetMapping("/getCoinDeskInfo")
    public CurrencyInfoVo getCoinDeskInfo() throws JsonProcessingException, ParseException {
        return coinDeskService.getCurrencyInfo();
    }

    @GetMapping("/getCoinDeskRaw")
    public String getCoinDeskRaw() throws JsonProcessingException, ParseException {
        return coinDeskService.getCoinDeskInfoRaw();
    }

}

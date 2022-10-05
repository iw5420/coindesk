package com.currency.coindesk.api;

import com.currency.coindesk.entity.Currency;
import com.currency.coindesk.entity.ResponseResult;
import com.currency.coindesk.service.CurrencyService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Log4j2
@RestController
@RequestMapping( value = "/currency")
public class CurrencyApi extends BaseApi{

    @Autowired
    CurrencyService currencyService;

    @GetMapping
    public ResponseResult findAll() {
        log.info( "findAll" );
        ResponseResult result = new ResponseResult();
        List<Currency> currencies = currencyService.findAll();
        return setResult(result, currencies,"OK","400");
    }

    @GetMapping( "/{code}" )
    public ResponseResult findByCode( @PathVariable String code ) {
        log.info( "findByCode : {}", code );
        ResponseResult result = new ResponseResult();
        Currency currencies = currencyService.findByCode(code);
        return setResult(result, currencies,"OK","400");
    }

    @PostMapping
    public ResponseResult insert( @Valid @RequestBody Currency currency ) {
        log.info( "insert : {}", currency );
        ResponseResult result = new ResponseResult();
        Currency savedCurrency = currencyService.insert(currency);
        return setResult(result, savedCurrency,"OK","400");
    }

    @PutMapping
    public ResponseResult update( @Valid @RequestBody Currency currency ) {
        log.info( "update : {}", currency );
        ResponseResult result = new ResponseResult();
        Currency savedCurrency = currencyService.update(currency);
        return setResult(result, savedCurrency,"OK","400");
    }

    @DeleteMapping( "/{code}" )
    public ResponseResult deleteByCode( @PathVariable( "code" ) String code ) {
        log.info( "deleteByCode : {}", code );
        ResponseResult result = new ResponseResult();
        currencyService.deleteByCode(code);
        return  setResult(result, null,"OK","400");
    }
}

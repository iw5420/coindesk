package com.currency.coindesk.function;

import com.currency.coindesk.dao.CurrencyDao;
import com.currency.coindesk.entity.CoindeskDto;
import com.currency.coindesk.entity.CoindeskTransDto;
import com.currency.coindesk.entity.Currency;
import com.currency.coindesk.service.CoindeskService;
import com.currency.coindesk.service.CurrencyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootTest
@Transactional
class TestsInDevelopment {

    @Autowired
    private CurrencyDao currencyDao;

    @Autowired
    private CoindeskService coindeskService;

    @Autowired
    private CurrencyService currencyService;

    //@Test
    void mapSelectAllCurrency() {
        List<Currency> currencyList = currencyDao.findAll();
        for (Currency currency : currencyList) {
            System.out.println(currency.getCode());
        }
        Map<String, String> currencyMap = currencyDao.findAll().stream()
                .collect(Collectors.toMap(Currency::getCode, Currency::getName));
        System.out.println(currencyMap);
    }

    //@Test
    void testOldData(){
        CoindeskDto coindeskDto =  coindeskService.getOriginalData();
        System.out.println(coindeskDto);
    }
    //@Test
    void testDateTime(){
        // ISO-8601 formatted string
        String str = "2009-12-02T11:25:25";
// parse string to `LocalDateTime`
        LocalDateTime dateTime = LocalDateTime.parse(str);
// print `LocalDateTime`
        System.out.println("Parsed LocalDateTime: " + dateTime);
        //有加號的情況要用
        //ZonedDateTime zonedDateTimeMontréal = odt.atZoneSameInstant( ZoneId.of( "America/Montreal" ) );
        //ZonedDateTime zonedDateTimeMontréal = odt.atZoneSameInstant( ZoneId.of( ZoneId.systemDefault() ) );
        //參考 https://stackoverflow.com/questions/2201925/converting-iso-8601-compliant-string-to-java-util-date
    }
    //@Test
    void testNewData(){
        CoindeskTransDto newData = coindeskService.getNewData();
        System.out.println(newData);
    }
    //@Test
    void testSelectByCode(){
        Currency currency = currencyService.findByCode("USD");
        System.out.println(currency);
    }

    //@Test
    void testInsert(){
        Currency newCurrency = new Currency();
        newCurrency.setCode("JPY");
        newCurrency.setName("日圓");
        newCurrency.setUpdate_date(LocalDateTime.now());
        currencyService.insert(newCurrency);
        List<Currency> currencyList = currencyDao.findAll();
        for (Currency currency : currencyList) {
            System.out.println(currency.getCode());
        }
    }

    //@Test
    void testUpdate(){
        Currency newCurrency = new Currency();
        newCurrency.setCode("USD");
        newCurrency.setName("日圓");
        newCurrency.setUpdate_date(LocalDateTime.now());
        currencyService.update(newCurrency);
        List<Currency> currencyList = currencyDao.findAll();
        for (Currency currency : currencyList) {
            System.out.println(currency.getCode()+currency.getName());
        }
    }
    //@Test
    void testDelete(){
        List<Currency> currencyList = currencyDao.findAll();
        for (Currency currency : currencyList) {
            System.out.println(currency.getCode()+currency.getName());
        }
        currencyService.deleteByCode("USD");
        currencyList = currencyDao.findAll();
        for (Currency currency : currencyList) {
            System.out.println(currency.getCode()+currency.getName());
        }
    }

}

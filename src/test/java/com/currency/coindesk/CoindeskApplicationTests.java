package com.currency.coindesk;

import com.currency.coindesk.dao.CurrencyDao;
import com.currency.coindesk.entity.CoindeskDto;
import com.currency.coindesk.entity.CoindeskTransDto;
import com.currency.coindesk.entity.Currency;
import com.currency.coindesk.service.CoindeskService;
import com.currency.coindesk.service.CurrencyService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@Transactional
@Log4j2
class CoindeskApplicationTests {

    @Autowired
    private CurrencyDao currencyDao;

    @Autowired
    private CoindeskService coindeskService;

    @Autowired
    private CurrencyService currencyService;

    // 1. 測試呼叫查詢幣別對應表資料 API，並顯示其內容。
    @Test
    void testFindAll(){
        log.info( "start test1---findAll" );
        List<Currency> currencyList = currencyDao.findAll();
        for (Currency currency : currencyList) {
            log.info(currency.getId()+"-"+currency.getCode()+"-"+currency.getName()+"-"+currency.getUpdate_date());
        }
        log.info( "end test1---findAll" );
    }

    // 2. 測試呼叫新增幣別對應表資料 API。
    @Test
    void testInsert(){
        log.info( "start test2---insert currency" );
        Currency newCurrency = new Currency();
        newCurrency.setCode("JPY");
        newCurrency.setName("日圓");
        newCurrency.setUpdate_date(LocalDateTime.now());
        currencyService.insert(newCurrency);
        List<Currency> currencyList = currencyDao.findAll();
        for (Currency currency : currencyList) {
            log.info(currency.getId()+"-"+currency.getCode()+"-"+currency.getName()+"-"+currency.getUpdate_date());
        }
        log.info( "end test2---insert currency" );
    }

    //3. 測試呼叫更新幣別對應表資料 API，並顯示其內容。
    @Test
    void testUpdate(){
        log.info( "start test3---update currency" );
        Currency newCurrency = new Currency();
        newCurrency.setCode("USD");
        newCurrency.setName("日圓");
        newCurrency.setUpdate_date(LocalDateTime.now());
        currencyService.update(newCurrency);
        List<Currency> currencyList = currencyDao.findAll();
        for (Currency currency : currencyList) {
            log.info(currency.getId()+"-"+currency.getCode()+"-"+currency.getName()+"-"+currency.getUpdate_date());
        }
        log.info( "end test3---update currency" );
    }
    
    //4. 測試呼叫刪除幣別對應表資料 API。
    @Test
    void testDelete(){
        log.info( "start test4---delete currency" );
        log.info( "before" );
        List<Currency> currencyList = currencyDao.findAll();
        for (Currency currency : currencyList) {
            System.out.println(currency.getCode()+currency.getName());
        }
        currencyService.deleteByCode("USD");
        currencyList = currencyDao.findAll();
        log.info( "after" );
        for (Currency currency : currencyList) {
            log.info(currency.getId()+"-"+currency.getCode()+"-"+currency.getName()+"-"+currency.getUpdate_date());
        }
        log.info( "end test4---delete currency" );
    }

    //5. 測試呼叫 coindesk API，並顯示其內容。
    @Test
    void testOldData(){
        log.info( "start test5---old api" );
        CoindeskDto coindeskDto =  coindeskService.getOriginalData();
        log.info(coindeskDto);
        log.info( "end test5---old api" );
    }

    //6. 測試呼叫資料轉換的 API，並顯示其內容。
    @Test
    void testNewData(){
        log.info( "start test6---new api" );
        CoindeskTransDto newData = coindeskService.getNewData();
        System.out.println(newData);
        log.info( "end test6---new api" );
    }

}

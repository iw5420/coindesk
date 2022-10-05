package com.currency.coindesk.service;

import com.currency.coindesk.dao.CurrencyDao;
import com.currency.coindesk.entity.Currency;
import com.currency.coindesk.exception.DuplicateException;
import com.currency.coindesk.exception.NotExistException;
import com.currency.coindesk.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CurrencyServiceImpl extends BaseService implements CurrencyService{

    @Autowired
    CurrencyDao currencyDao;

    @Override
    public List<Currency> findAll() {
        return currencyDao.findAll();
    }

    @Override
    public Currency findByCode(String code) {
        List<Currency> currencies = currencyDao.findByCode(code);
        if (currencies.size()>0){
            return currencies.get(0);
        }else{
            throw new NotFoundException( "Can't find Currency code " + code + " .");
        }
    }

    @Override
    public Currency insert(Currency currency) {
        if(currencyDao.findByCode(currency.getCode()).size()>0)
            throw new DuplicateException(currency.getCode());
        currency.setUpdate_date(LocalDateTime.now());
        Currency savedCurrency = currencyDao.save(currency);
        currencyMap.put(savedCurrency.getCode(),savedCurrency.getName());
        return savedCurrency;
    }

   @Override
   public Currency update(Currency currency) {
        if (currencyDao.findByCode(currency.getCode()).size()==0)
                throw new NotExistException(currency.getCode());

        Currency pastCurrency = currencyDao.findByCode(currency.getCode()).get(0);
        pastCurrency.setName(currency.getName());
        pastCurrency.setUpdate_date(LocalDateTime.now());
        Currency savedCurrency = currencyDao.save(pastCurrency);
        currencyMap.put(savedCurrency.getCode(),savedCurrency.getName());

        return savedCurrency;
    }

    @Override
    public void deleteByCode(String code) {
        if(currencyDao.findByCode(code).size()==0)
            throw new NotExistException(code);
        Currency pastCurrency = currencyDao.findByCode(code).get(0);
        currencyDao.deleteById(pastCurrency.getId());
        currencyMap.remove(code);
    }
}

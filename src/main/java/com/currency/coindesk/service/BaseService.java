package com.currency.coindesk.service;

import com.currency.coindesk.dao.CurrencyDao;
import com.currency.coindesk.entity.Currency;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Transactional
public class BaseService {

    public static Map<String, String> currencyMap = new HashMap<>();

    @Autowired
    private CurrencyDao currencyDao;

    @PostConstruct
    private void init() {
        currencyMap = currencyDao.findAll()
                .stream()
                .collect( Collectors.toMap( Currency::getCode, Currency::getName ));
    }
}

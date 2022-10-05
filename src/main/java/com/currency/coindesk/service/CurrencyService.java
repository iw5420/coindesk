package com.currency.coindesk.service;

import com.currency.coindesk.entity.Currency;

import java.util.List;

public interface CurrencyService {
    List<Currency> findAll();

    Currency findByCode(String code);

    Currency insert(Currency currency);

    Currency update(Currency currency);

    void deleteByCode(String code);
}

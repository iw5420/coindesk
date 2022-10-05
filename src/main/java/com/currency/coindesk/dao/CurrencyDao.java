package com.currency.coindesk.dao;

import com.currency.coindesk.entity.Currency;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface CurrencyDao extends CrudRepository<Currency, Long> {
    List<Currency> findAll();
    List<Currency> findByCode(String code);
}

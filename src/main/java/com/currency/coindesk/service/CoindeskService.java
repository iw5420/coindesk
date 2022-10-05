package com.currency.coindesk.service;

import com.currency.coindesk.entity.CoindeskDto;
import com.currency.coindesk.entity.CoindeskTransDto;

public interface CoindeskService {

    public CoindeskDto getOriginalData();

    public CoindeskTransDto getNewData();

}

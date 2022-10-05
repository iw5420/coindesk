package com.currency.coindesk.api;

import com.currency.coindesk.entity.CoindeskDto;
import com.currency.coindesk.entity.CoindeskTransDto;
import com.currency.coindesk.entity.ResponseResult;
import com.currency.coindesk.service.CoindeskService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping(value = "/coindesk")
public class CoindeskApi extends BaseApi{

    @Autowired
    CoindeskService coindeskService;

    @GetMapping(value = "original")
    public ResponseResult getDataFromOldApi(){
        log.info( "Call getDataFromOldApi" );
        ResponseResult result = new ResponseResult();
        CoindeskDto oldData = coindeskService.getOriginalData();
        return setResult(result, oldData,"OK","400");
    }

    @GetMapping(value = "transform")
    public ResponseResult getDataFromNewApi(){
        log.info( "Call getDataFromNewApi" );
        ResponseResult result = new ResponseResult();
        CoindeskTransDto newData = coindeskService.getNewData();
        return setResult(result, newData,"OK","400");
    }

}

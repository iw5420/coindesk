package com.currency.coindesk.api;

import com.currency.coindesk.entity.ResponseResult;

public class BaseApi {
    public ResponseResult setResult(ResponseResult result, Object obj, String message, String code){
        result.setCode(code);
        result.setContent(obj);
        result.setMsg(message);
        return result;
    }
}

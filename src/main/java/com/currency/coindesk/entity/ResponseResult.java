package com.currency.coindesk.entity;

import lombok.Data;

@Data
public class ResponseResult {
    private String code = "";
    private String msg;
    private Object content;
}

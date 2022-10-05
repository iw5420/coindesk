package com.currency.coindesk.entity;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class CoindeskTransDto {
    private String updateTime;
    private Map<String, TransBpiDto> bpi = new HashMap<>();

    @Data
    public static class TransBpiDto {
        private String code;
        private String name;
        private Double rate;
    }
}

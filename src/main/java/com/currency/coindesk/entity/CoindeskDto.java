package com.currency.coindesk.entity;

import lombok.Data;
import lombok.ToString;
import java.util.Map;

@Data
@ToString
public class CoindeskDto {
    private TimeDto time;
    private String disclaimer;
    private String chartName;
    private Map<String, BpiDto> bpi;

    @Data
    public static class TimeDto {
        private String updated;
        private String updatedISO;
        private String updateduk;
    }

    @Data
    public static class BpiDto {
        private String code;
        private String symbol;
        private String rate;
        private String description;
        private Double rate_float;
    }

}

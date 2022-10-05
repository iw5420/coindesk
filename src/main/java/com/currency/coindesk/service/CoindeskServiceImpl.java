package com.currency.coindesk.service;

import com.currency.coindesk.entity.CoindeskDto;
import com.currency.coindesk.entity.CoindeskTransDto;
import com.currency.coindesk.exception.TransformException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import com.currency.coindesk.entity.CoindeskTransDto.TransBpiDto;

@Service
public class CoindeskServiceImpl extends BaseService implements CoindeskService{

    @Autowired
    private ObjectMapper objectMapper;

    @Value( "${coindesk.url}" )
    private String coindeskUrl;

    @Override
    public CoindeskDto getOriginalData() {
        String result = null;
        CoindeskDto oldData;
        RestTemplate restTemplate = new RestTemplate();
        try {
            result = restTemplate.getForObject(coindeskUrl, String.class);
            oldData = objectMapper.readValue( result, CoindeskDto.class );
        }catch(Exception e){
            e.printStackTrace();
            throw new TransformException("getDataFromOldApi");
        }
        return oldData;
    }

    @Override
    public CoindeskTransDto getNewData() {
        CoindeskTransDto newData = new CoindeskTransDto();
        try {
            CoindeskDto oldData = this.getOriginalData();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            ZonedDateTime updateTime = OffsetDateTime.parse( oldData.getTime().getUpdatedISO() ).atZoneSameInstant( ZoneId.systemDefault() );

            newData.setUpdateTime(formatter.format(updateTime));
            oldData.getBpi().forEach((k, v) -> {
                TransBpiDto bpi = newData.getBpi().computeIfAbsent(k, m -> new TransBpiDto());
                bpi.setCode(v.getCode());
                bpi.setName(currencyMap.getOrDefault(v.getCode(), v.getCode()));
                bpi.setRate(v.getRate_float());
            });
            return newData;
        } catch (Exception e){
            e.printStackTrace();
            throw new TransformException("getDataFromNewApi");
        }
    }
}

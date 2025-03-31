package com.zoombit.domain.dto;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataDTO {

    // 전역 date 필드
    private String date;

    // 코인 데이터를 저장할 맵. 키는 코인 심볼, 값은 TickerV2DTO
    private Map<String, TickerV2DTO> coinData = new HashMap<>();

    // JSON의 data 객체에 존재하는 모든 필드를 처리
    @JsonAnySetter
    public void handleUnknown(String key, Object value) {
        if ("date".equals(key)) {
            this.date = value.toString();
        } else {
            // 나머지 키는 코인 데이터로 간주하여 맵에 저장합니다.
            // (ObjectMapper를 사용해서 TickerV2DTO로 변환)
            TickerV2DTO ticker = new com.fasterxml.jackson.databind.ObjectMapper().convertValue(value, TickerV2DTO.class);
            coinData.put(key, ticker);
        }
    }
}

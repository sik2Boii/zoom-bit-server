package com.zoombit.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zoombit.domain.dto.MarketDTO;
import com.zoombit.domain.entity.Markets;
import com.zoombit.repository.MarketRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BithumbApiService {

    public static final Logger logger = LoggerFactory.getLogger(BithumbApiService.class.getName());

    @Autowired
    private MarketRepository marketRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    private final String API_MARKET_URL = "https://api.bithumb.com/v1/market/all"; // 마켓 코드 조회

    public void getAllMarkets() {

        RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
        String response = restTemplate.getForObject(API_MARKET_URL, String.class);

        List<MarketDTO> marketDtos = null;

        try {
            marketDtos = objectMapper.readValue(response, new TypeReference<List<MarketDTO>>() {});
        } catch (JsonProcessingException e) {
            logger.error("JSON 처리 중 오류 발생: " + e.getMessage());
            throw new RuntimeException("JSON 데이터 처리 실패", e);
        }

        for (MarketDTO dto : marketDtos) {
            Markets market = new Markets();
            market.setMarket(dto.getMarket());
            market.setKoreanName(dto.getKorean_name());
            market.setEnglishName(dto.getEnglish_name());
            marketRepository.save(market);
        }

    }

}

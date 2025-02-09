package com.zoombit.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.util.concurrent.RateLimiter;
import com.zoombit.domain.dto.MarketDTO;
import com.zoombit.domain.dto.TickerDTO;
import com.zoombit.domain.entity.Markets;
import com.zoombit.repository.MarketRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class BithumbApiService {

    public static final Logger logger = LoggerFactory.getLogger(BithumbApiService.class.getName());

    @Autowired
    private MarketRepository marketRepository;

    ObjectMapper objectMapper = new ObjectMapper();
    RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());

    private final String API_MARKET_URL = "https://api.bithumb.com/v1/market/all"; // 마켓 코드 조회
    private final String API_TICKER_URL = "https://api.bithumb.com/v1/ticker";

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

    public void getTicker(String ticker) {

        String url = API_TICKER_URL + "?markets=" + ticker;
        String response = restTemplate.getForObject(url, String.class);

        TickerDTO tickerDTO = null;

        try {
            List<TickerDTO> tickerDTOS = objectMapper.readValue(response, new TypeReference<List<TickerDTO>>() {});

            if (!tickerDTOS.isEmpty()) {
                tickerDTO = tickerDTOS.get(0);
            } else {
                throw new RuntimeException("응답 데이터가 비어 있습니다.");
            }

        } catch (JsonProcessingException e) {
            logger.error("JSON 처리 중 오류 발생: " + e.getMessage());
            throw new RuntimeException("JSON 데이터 처리 실패", e);
        }

        System.out.println("받은 Ticker 정보: " + tickerDTO);

    }

    public void getAllTicker() {

        List<String> marketIds = getAllMarketIds();
        RateLimiter rateLimiter = RateLimiter.create(140);

        for (String marketId : marketIds) {
            rateLimiter.acquire();
            getTicker(marketId);
        }

    }

    public List<String> getAllMarketIds() {
        return marketRepository.findAllMarketIds();
    }

}

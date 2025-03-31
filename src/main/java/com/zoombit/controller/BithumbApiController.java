package com.zoombit.controller;

import com.zoombit.service.BithumbApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BithumbApiController {

    public static final Logger logger = LoggerFactory.getLogger(BithumbApiController.class.getName());

    @Autowired
    private BithumbApiService bithumbApiService;

    @GetMapping("/getMarkets")
    public ResponseEntity<String> saveMarkets() {
        try {
            bithumbApiService.getAllMarkets();
            return ResponseEntity.ok("All markets saved successfully.");
        } catch (Exception e) {
            logger.error("Failed to save all markets.", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to save all markets.");
        }
    }

    @GetMapping("/getTicker")
    public ResponseEntity<String> getTicker(@RequestParam String market) {
        try {
            bithumbApiService.getTicker(market);
            return ResponseEntity.ok("market: " + market + " Current price retrieval successful");
        } catch (Exception e) {
            return new ResponseEntity<>("market: " + market + " Failed to current price", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAllTicker")
    public ResponseEntity<String> getAllTicker() {
        try {
            bithumbApiService.getAllTicker();
            return ResponseEntity.ok("All market current price retrieval successful");
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to current price", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAllTickerV2")
    public ResponseEntity<String> getAllTickerV2() {
        try {
            bithumbApiService.getAllTickerV2();
            return ResponseEntity.ok("All market current price retrieval successful");
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to current price", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

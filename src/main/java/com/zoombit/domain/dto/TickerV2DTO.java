package com.zoombit.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TickerV2DTO {

    private String opening_price;
    private String closing_price;
    private String min_price;
    private String max_price;
    private String units_traded;
    private String acc_trade_value;
    private String prev_closing_price;
    private String units_traded_24H;
    private String acc_trade_value_24H;
    private String fluctate_24H;
    private String fluctate_rate_24H;

    public String getOpening_price() {
        return opening_price;
    }

    public void setOpening_price(String opening_price) {
        this.opening_price = opening_price;
    }

    public String getClosing_price() {
        return closing_price;
    }

    public void setClosing_price(String closing_price) {
        this.closing_price = closing_price;
    }

    public String getMin_price() {
        return min_price;
    }

    public void setMin_price(String min_price) {
        this.min_price = min_price;
    }

    public String getMax_price() {
        return max_price;
    }

    public void setMax_price(String max_price) {
        this.max_price = max_price;
    }

    public String getUnits_traded() {
        return units_traded;
    }

    public void setUnits_traded(String units_traded) {
        this.units_traded = units_traded;
    }

    public String getAcc_trade_value() {
        return acc_trade_value;
    }

    public void setAcc_trade_value(String acc_trade_value) {
        this.acc_trade_value = acc_trade_value;
    }

    public String getPrev_closing_price() {
        return prev_closing_price;
    }

    public void setPrev_closing_price(String prev_closing_price) {
        this.prev_closing_price = prev_closing_price;
    }

    public String getUnits_traded_24H() {
        return units_traded_24H;
    }

    public void setUnits_traded_24H(String units_traded_24H) {
        this.units_traded_24H = units_traded_24H;
    }

    public String getAcc_trade_value_24H() {
        return acc_trade_value_24H;
    }

    public void setAcc_trade_value_24H(String acc_trade_value_24H) {
        this.acc_trade_value_24H = acc_trade_value_24H;
    }

    public String getFluctate_24H() {
        return fluctate_24H;
    }

    public void setFluctate_24H(String fluctate_24H) {
        this.fluctate_24H = fluctate_24H;
    }

    public String getFluctate_rate_24H() {
        return fluctate_rate_24H;
    }

    public void setFluctate_rate_24H(String fluctate_rate_24H) {
        this.fluctate_rate_24H = fluctate_rate_24H;
    }

}

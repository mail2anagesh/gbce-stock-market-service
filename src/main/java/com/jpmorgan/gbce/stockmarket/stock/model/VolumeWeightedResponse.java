package com.jpmorgan.gbce.stockmarket.stock.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Volume Weighted Response Class
 */
@Setter
@Getter
public class VolumeWeightedResponse {
    @ApiModelProperty(required = true, value = "symbol of stock")
    private String symbol;

    @ApiModelProperty(required = true, value = "Volume Weighted price")
    private BigDecimal volWeighedPrice;

    private VolumeWeightedResponse(Builder builder) {
        this.symbol = builder.symbol;
        this.volWeighedPrice = builder.volWeighedPrice;
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();
        builder.append("VolumeWeightedResponse [symbol=").append(symbol).append(", volWeighedPrice=");
        builder.append(volWeighedPrice).append("]");
        return builder.toString();
    }

    public static class Builder {
        private String symbol;
        private BigDecimal volWeighedPrice;

        public Builder() {
        }

        public Builder(String symbol, BigDecimal volWeighedPrice) {
            this.symbol = symbol;
            this.volWeighedPrice = volWeighedPrice;
        }

        public Builder symbol(final String symbol) {
            this.symbol = symbol;
            return this;
        }

        public Builder volWeighedPrice(final BigDecimal volWeighedPrice) {
            this.volWeighedPrice = volWeighedPrice;
            return this;
        }

        public VolumeWeightedResponse build() {
            return new VolumeWeightedResponse(this);
        }
    }

}

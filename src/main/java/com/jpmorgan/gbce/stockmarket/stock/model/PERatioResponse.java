package com.jpmorgan.gbce.stockmarket.stock.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * PE Ratio Response Class
 */
@Setter
@Getter
public class PERatioResponse {
    @ApiModelProperty(required = true, value = "symbol of stock")
    private String symbol;

    @ApiModelProperty(required = true, value = "stock price")
    private BigDecimal stockPrice;

    @ApiModelProperty(required = true, value = "PE Ratio of stock")
    private BigDecimal peRatio;

    private PERatioResponse(Builder builder) {
        this.symbol = builder.symbol;
        this.stockPrice = builder.stockPrice;
        this.peRatio = builder.peRatio;
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();
        builder.append("PERatioResponse [symbol=").append(symbol).append(", stockPrice=").append(stockPrice);
        builder.append(", peRatio=").append(peRatio).append("]");
        return builder.toString();
    }

    public static class Builder {
        private String symbol;
        private BigDecimal stockPrice;
        private BigDecimal peRatio;

        public Builder() {
        }

        public Builder(String symbol, BigDecimal stockPrice, BigDecimal peRatio) {
            this.symbol = symbol;
            this.stockPrice = stockPrice;
            this.peRatio = peRatio;
        }

        public Builder symbol(final String symbol) {
            this.symbol = symbol;
            return this;
        }

        public Builder stockPrice(final BigDecimal stockPrice) {
            this.stockPrice = stockPrice;
            return this;
        }

        public Builder peRatio(final BigDecimal peRatio) {
            this.peRatio = peRatio;
            return this;
        }

        public PERatioResponse build() {
            return new PERatioResponse(this);
        }
    }

}

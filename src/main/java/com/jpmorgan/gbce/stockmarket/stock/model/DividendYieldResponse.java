package com.jpmorgan.gbce.stockmarket.stock.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Dividend Yield Response Class
 */
@Setter
@Getter
public class DividendYieldResponse {
    @ApiModelProperty(required = true, value = "stock symbol")
    private String symbol;

    @ApiModelProperty(required = true, value = "stock price")
    private BigDecimal stockPrice;

    @ApiModelProperty(required = true, value = "dividend yield of stock")
    private BigDecimal dividendYield;

    private DividendYieldResponse(Builder builder) {
        this.symbol = builder.symbol;
        this.stockPrice = builder.stockPrice;
        this.dividendYield = builder.dividendYield;
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();
        builder.append("DividendYieldResponse [symbol=").append(symbol).append(", stockPrice=").append(stockPrice);
        builder.append(", dividendYield=").append(dividendYield).append("]");
        return builder.toString();
    }

    public static class Builder {
        private String symbol;
        private BigDecimal stockPrice;
        private BigDecimal dividendYield;

        public Builder() {
        }

        public Builder(String symbol, BigDecimal stockPrice, BigDecimal dividendYield) {
            this.symbol = symbol;
            this.stockPrice = stockPrice;
            this.dividendYield = dividendYield;
        }

        public Builder symbol(final String symbol) {
            this.symbol = symbol;
            return this;
        }

        public Builder stockPrice(final BigDecimal stockPrice) {
            this.stockPrice = stockPrice;
            return this;
        }

        public Builder dividendYield(final BigDecimal dividendYield) {
            this.dividendYield = dividendYield;
            return this;
        }

        public DividendYieldResponse build() {
            return new DividendYieldResponse(this);
        }
    }

}

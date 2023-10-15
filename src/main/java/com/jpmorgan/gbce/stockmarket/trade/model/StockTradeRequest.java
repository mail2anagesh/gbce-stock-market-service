package com.jpmorgan.gbce.stockmarket.trade.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Stock Trade Request class
 */
@Setter
@Getter
public class StockTradeRequest {

    @ApiModelProperty(required = true, value = "stock symbol")
    @NotNull
    private String symbol;

    @ApiModelProperty(required = true, value = "trade type")
    @NotNull
    private TradeType type;

    @ApiModelProperty(required = true, value = "stock price")
    @NotNull
    private BigDecimal tradePrice;

    @ApiModelProperty(required = true, value = "trade quantity")
    @NotNull
    @Min(1)
    private Long sharesQuantity;

    @Override
    public int hashCode() {

        final int prime = 48;
        int result = 1;
        result = prime * result + ((sharesQuantity == null) ? 0 : sharesQuantity.hashCode());
        result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
        result = prime * result + ((tradePrice == null) ? 0 : tradePrice.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        StockTradeRequest other = (StockTradeRequest) obj;
        if (sharesQuantity == null) {
            if (other.sharesQuantity != null)
                return false;
        } else if (!sharesQuantity.equals(other.sharesQuantity))
            return false;
        if (symbol == null) {
            if (other.symbol != null)
                return false;
        } else if (!symbol.equals(other.symbol))
            return false;
        if (tradePrice == null) {
            if (other.tradePrice != null)
                return false;
        } else if (!tradePrice.equals(other.tradePrice))
            return false;
        return type == other.type;
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();
        builder.append("StockTradeRequest [symbol=").append(symbol).append(", type=").append(type).append(", tradePrice=");
        builder.append(tradePrice).append(", sharesQuantity=").append(sharesQuantity).append("]");
        return builder.toString();
    }

}

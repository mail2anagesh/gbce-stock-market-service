package com.jpmorgan.gbce.stockmarket.trade.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * Trade class
 */
@Setter
@Getter
public class Trade {

    private final String symbol;
    private final TradeType type;
    private final BigDecimal tradedPrice;
    private final Long sharesQuantity;
    private final Instant timestamp;

    private Trade(Builder builder) {
        this.symbol = builder.symbol;
        this.type = builder.type;
        this.tradedPrice = builder.tradedPrice;
        this.sharesQuantity = builder.sharesQuantity;
        this.timestamp = builder.timestamp;
    }

    @Override
    public int hashCode() {

        final int prime = 48;
        int result = 1;
        result = prime * result + ((sharesQuantity == null) ? 0 : sharesQuantity.hashCode());
        result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
        result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
        result = prime * result + ((tradedPrice == null) ? 0 : tradedPrice.hashCode());
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
        Trade other = (Trade) obj;
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
        if (timestamp == null) {
            if (other.timestamp != null)
                return false;
        } else if (!timestamp.equals(other.timestamp))
            return false;
        if (tradedPrice == null) {
            if (other.tradedPrice != null)
                return false;
        } else if (!tradedPrice.equals(other.tradedPrice))
            return false;
        return type == other.type;
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();
        builder.append("Trade [symbol=").append(symbol).append(", type=").append(type).append(", tradedPrice=");
        builder.append(tradedPrice).append(", sharesQuantity=").append(sharesQuantity).append(", timestamp=").append(timestamp);
        builder.append("]");
        return builder.toString();
    }

    public static class Builder {
        private String symbol;
        private TradeType type;
        private BigDecimal tradedPrice;
        private Long sharesQuantity;
        private Instant timestamp;

        public Builder(String symbol, TradeType type, BigDecimal tradedPrice, Long sharesQuantity, Instant timestamp) {
            this.symbol = symbol;
            this.type = type;
            this.tradedPrice = tradedPrice;
            this.sharesQuantity = sharesQuantity;
            this.timestamp = timestamp;
        }

        public Builder symbol(final String symbol) {
            this.symbol = symbol;
            return this;
        }

        public Builder type(final TradeType type) {
            this.type = type;
            return this;
        }

        public Builder tradedPrice(final BigDecimal tradedPrice) {
            this.tradedPrice = tradedPrice;
            return this;
        }

        public Builder sharesQuantity(final Long sharesQuantity) {
            this.sharesQuantity = sharesQuantity;
            return this;
        }

        public Builder timestamp(final Instant timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Trade build() {
            return new Trade(this);
        }
    }
}

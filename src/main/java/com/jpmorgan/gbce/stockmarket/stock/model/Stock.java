package com.jpmorgan.gbce.stockmarket.stock.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Stock Class
 */
@Setter
@Getter
public class Stock {

    private long id;
    private String symbol;
    private StockType type;
    private BigDecimal lastDividend;
    private BigDecimal fixedDividend;
    private BigDecimal parValue;

    private Stock(Builder builder) {
        this.id = builder.id;
        this.symbol = builder.symbol;
        this.type = builder.type;
        this.lastDividend = builder.lastDividend;
        this.fixedDividend = builder.fixedDividend;
        this.parValue = builder.parValue;
    }

    @Override
    public int hashCode() {
        final int prime = 48;
        int result = 1;
        result = prime * result + ((fixedDividend == null) ? 0 : fixedDividend.hashCode());
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + ((lastDividend == null) ? 0 : lastDividend.hashCode());
        result = prime * result + ((parValue == null) ? 0 : parValue.hashCode());
        result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
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
        Stock other = (Stock) obj;
        if (fixedDividend == null) {
            if (other.fixedDividend != null)
                return false;
        } else if (!fixedDividend.equals(other.fixedDividend))
            return false;
        if (id != other.id)
            return false;
        if (lastDividend == null) {
            if (other.lastDividend != null)
                return false;
        } else if (!lastDividend.equals(other.lastDividend))
            return false;
        if (parValue == null) {
            if (other.parValue != null)
                return false;
        } else if (!parValue.equals(other.parValue))
            return false;
        if (symbol == null) {
            if (other.symbol != null)
                return false;
        } else if (!symbol.equals(other.symbol))
            return false;
        return type == other.type;
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();
        builder.append("Stock [id=").append(id).append(", symbol=").append(symbol).append(", type=").append(type);
        builder.append(", lastDividend=").append(lastDividend).append(", fixedDividend=").append(fixedDividend).append(", parValue=");
        builder.append(parValue).append("]");
        return builder.toString();
    }

    public static class Builder {

        private long id;
        private String symbol;
        private StockType type;
        private BigDecimal lastDividend;
        private BigDecimal fixedDividend;
        private BigDecimal parValue;

        public Builder() {
        }

        public Builder(String symbol, StockType type, BigDecimal lastDividend, BigDecimal fixedDividend, BigDecimal parValue) {
            this.symbol = symbol;
            this.type = type;
            this.lastDividend = lastDividend;
            this.fixedDividend = fixedDividend;
            this.parValue = parValue;
        }

        public Builder symbol(final String symbol) {
            this.symbol = symbol;
            return this;
        }

        public Builder type(final StockType type) {
            this.type = type;
            return this;
        }

        public Builder lastDividend(final BigDecimal lastDividend) {
            this.lastDividend = lastDividend;
            return this;
        }

        public Builder fixedDividend(final BigDecimal fixedDividend) {
            this.fixedDividend = fixedDividend;
            return this;
        }

        public Builder parValue(final BigDecimal parValue) {
            this.parValue = parValue;
            return this;
        }

        public Stock build() {
            return new Stock(this);
        }
    }

}

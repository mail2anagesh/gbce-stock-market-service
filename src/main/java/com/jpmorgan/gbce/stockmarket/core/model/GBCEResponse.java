package com.jpmorgan.gbce.stockmarket.core.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * GBCE Response Class
 */
@Setter
@Getter
public class GBCEResponse {

    @ApiModelProperty(required = true, value = "GBCE of all Shares")
    private BigDecimal gbce;

    private GBCEResponse(Builder builder) {
        this.gbce = builder.gbce;
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();
        builder.append("GBCEResponse [gbce=").append(gbce).append("]");
        return builder.toString();
    }

    public static class Builder {
        private BigDecimal gbce;

        public Builder() {
        }

        public Builder(BigDecimal gbce) {
            this.gbce = gbce;
        }

        public Builder gbce(final BigDecimal gbce) {
            this.gbce = gbce;
            return this;
        }

        public GBCEResponse build() {
            return new GBCEResponse(this);
        }
    }

}

package com.jpmorgan.gbce.stockmarket.core.service.impl;

import com.jpmorgan.gbce.stockmarket.core.service.StockMarketService;
import com.jpmorgan.gbce.stockmarket.core.utils.Constants;
import com.jpmorgan.gbce.stockmarket.trade.model.Trade;
import com.jpmorgan.gbce.stockmarket.trade.service.TradeService;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Class provides methods for Stock Market Services
 */
@Service
public class StockMarketServiceImpl implements StockMarketService {

    private static final Logger log = LoggerFactory.getLogger(StockMarketServiceImpl.class);
    @Autowired
    private TradeService tradeService;

    /**
     * Method to calcualte GBCE
     *
     * @return
     */
    @Override
    public BigDecimal calculateGBCE() {

        List<Trade> trades = tradeService.getAllTrades();
        BigDecimal priceProduct = BigDecimal.ONE;
        if (CollectionUtils.isEmpty(trades)) {
            log.warn("No Trades found");
            return priceProduct;
        }

        for (Trade trade : trades) {
            priceProduct = priceProduct.multiply(trade.getTradedPrice());
        }
        double n = BigDecimal.ONE.divide(BigDecimal.valueOf(trades.size()), Constants.MATH_CONTEXT).doubleValue();
        BigDecimal gbce = BigDecimal.valueOf(Math.pow(priceProduct.doubleValue(), n));

        log.debug("GBCE All Share Index [{}]", gbce);
        return gbce;
    }

}

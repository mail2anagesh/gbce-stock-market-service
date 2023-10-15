package com.jpmorgan.gbce.stockmarket.trade.database;

import com.jpmorgan.gbce.stockmarket.trade.model.Trade;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Class to handle the stock market data
 */
@Component
public class StockTradeDBUtil {

    private Map<String, List<Trade>> stockTradeData;

    /*
     * Load Stock Data in the Map
     */
    @PostConstruct
    private void initialize() {
        if (stockTradeData == null) {
            stockTradeData = new ConcurrentHashMap<>();
        }
    }

    public Map<String, List<Trade>> getStockMarketData() {
        if (stockTradeData == null) {
            initialize();
        }
        return stockTradeData;
    }
}

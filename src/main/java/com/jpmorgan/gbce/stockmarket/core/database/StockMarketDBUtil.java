package com.jpmorgan.gbce.stockmarket.core.database;

import com.jpmorgan.gbce.stockmarket.stock.model.Stock;
import com.jpmorgan.gbce.stockmarket.stock.model.StockType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Class to load the stock market data from property file
 */
@Component
public class StockMarketDBUtil {

    @Value("${gbce.data.symbol:TEA,POP,ALE,GIN,JOE}")
    private String stockSymbols;

    @Value("${gbce.data.type:Common,Common,Common,Preferred,Common}")
    private String stockTypes;

    @Value("${gbce.data.lastDividend:0,8,23,8,13}")
    private String stockLastDividends;

    @Value("${gbce.data.fixedDividend:0,0,0,2,0}")
    private String stockFixedDividends;

    @Value("${gbce.data.parValue:100,100,60,100,250}")
    private String stockParValues;

    private Map<String, Stock> stockMarketData;

    /**
     * Method to load the stock market data from property file
     */
    @PostConstruct
    private void initialize() {

        if (stockMarketData == null) {

            String[] symbols = null;
            if (StringUtils.isNotEmpty(stockSymbols)) {
                symbols = stockSymbols.split(",");
            }
            stockMarketData = new ConcurrentHashMap<>();
            String[] types = stockTypes.split(",");
            String[] lastDividends = stockLastDividends.split(",");
            String[] fixedDividends = stockFixedDividends.split(",");
            String[] parValues = stockParValues.split(",");

            for (int i = 0; i < symbols.length; i++) {
                Stock stock = new Stock.Builder(symbols[i], StockType.valueOf(types[i]), new BigDecimal(lastDividends[i]), new BigDecimal(fixedDividends[i]),
                        new BigDecimal(parValues[i])).build();
                stockMarketData.put(symbols[i], stock);
            }
        }
    }

    public Map<String, Stock> getStockMarketData() {

        if (stockMarketData == null) {
            initialize();
        }
        return stockMarketData;
    }
}

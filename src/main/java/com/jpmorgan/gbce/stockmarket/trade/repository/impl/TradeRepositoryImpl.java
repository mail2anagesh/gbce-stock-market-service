package com.jpmorgan.gbce.stockmarket.trade.repository.impl;

import com.jpmorgan.gbce.stockmarket.common.repository.impl.BaseRepositoryImpl;
import com.jpmorgan.gbce.stockmarket.trade.database.StockTradeDBUtil;
import com.jpmorgan.gbce.stockmarket.trade.model.Trade;
import com.jpmorgan.gbce.stockmarket.trade.repository.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
 * Class for Trade Repository Implementation
 */
@Repository
public class TradeRepositoryImpl extends BaseRepositoryImpl<String, List<Trade>> implements TradeRepository {

    @Autowired
    private StockTradeDBUtil stockTradeDBUtil;

    /**
     * Method gets stock market data
     *
     * @return
     */
    @Override
    protected Map<String, List<Trade>> getStockMarketData() {
        return stockTradeDBUtil.getStockMarketData();
    }

    /**
     * Method gets create trade
     *
     * @param key
     * @param value
     * @return
     */
    @Override
    public Trade createTrade(String key, Trade value) {

        List<Trade> trades = retrieveByKey(key);
        if (trades == null) {
            trades = new ArrayList<Trade>();
        }
        trades.add(value);
        insert(key, trades);
        return value;
    }

    /**
     * Method gets trades done in last xx minutes
     *
     * @param key
     * @param minutes
     * @return
     */
    @Override
    public List<Trade> tradesInlastMinutes(String key, int minutes) {

        List<Trade> tradesInLastMinutes = null;
        final Instant lastMinutesTime = Instant.now().minus(minutes, ChronoUnit.MINUTES);

        List<Trade> listTrades = retrieveByKey(key);
        if (listTrades != null) {
            tradesInLastMinutes = listTrades
                    .stream()
                    .filter(trade -> trade.getTimestamp().isAfter(lastMinutesTime))
                    .collect(Collectors.toList());
        }
        return tradesInLastMinutes;
    }

    /**
     * Method gets all the trades
     *
     * @return
     */
    @Override
    public List<Trade> getAllTrades() {

        List<Trade> trades = fetchAll().stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
        return trades;
    }

}

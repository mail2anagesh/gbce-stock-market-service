package com.jpmorgan.gbce.stockmarket.trade.service;

import com.jpmorgan.gbce.stockmarket.trade.model.StockTradeRequest;
import com.jpmorgan.gbce.stockmarket.trade.model.Trade;

import java.util.List;

/**
 * Interface for Trade Service
 */
public interface TradeService {

    void createTrade(StockTradeRequest tradeRequest);

    List<Trade> getTradesByLastMinutes(String symbol, int minutes);

    List<Trade> getAllTrades();

}

package com.jpmorgan.gbce.stockmarket.trade.service.impl;

import com.jpmorgan.gbce.stockmarket.stock.service.StockService;
import com.jpmorgan.gbce.stockmarket.stock.service.impl.StockServiceImpl;
import com.jpmorgan.gbce.stockmarket.trade.model.StockTradeRequest;
import com.jpmorgan.gbce.stockmarket.trade.model.Trade;
import com.jpmorgan.gbce.stockmarket.trade.repository.TradeRepository;
import com.jpmorgan.gbce.stockmarket.trade.service.TradeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Class provides methods for Trade Services
 */
@Service
public class TradeServiceImpl implements TradeService {

    private static final Logger log = LoggerFactory.getLogger(StockServiceImpl.class);
    @Autowired
    private TradeRepository tradeRepository;
    @Autowired
    private StockService stockService;

    /**
     * Method to create Trade
     *
     * @param tradeRequest
     */
    @Override
    public void createTrade(StockTradeRequest tradeRequest) {

        stockService.getStockDetails(tradeRequest.getSymbol());
        Trade trade = new Trade.Builder(tradeRequest.getSymbol(), tradeRequest.getType(), tradeRequest.getTradePrice(),
                tradeRequest.getSharesQuantity(), new Date()).build();
        tradeRepository.createTrade(tradeRequest.getSymbol(), trade);
        log.debug("Trade created Successful");
    }

    /**
     * Method to get trades done in last xx minutes
     *
     * @param symbol
     * @param minutes
     * @return
     */
    @Override
    public List<Trade> getTradesByLastMinutes(String symbol, int minutes) {

        log.debug("Fetching Trades in last {} minutes, symbol {} ", minutes, symbol);
        List<Trade> trades = tradeRepository.tradesInlastMinutes(symbol, minutes);
        log.debug("Trades in last {} minutes, symbol {} , trades {} ", symbol, minutes, trades);
        return trades;
    }

    /**
     * Method to get all the trades
     *
     * @return
     */
    @Override
    public List<Trade> getAllTrades() {

        List<Trade> trades = tradeRepository.getAllTrades();
        log.debug("All Trades details [{}]", trades);
        return trades;
    }

}

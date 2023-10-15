package com.jpmorgan.gbce.stockmarket.trade.repository;

import com.jpmorgan.gbce.stockmarket.common.repository.BaseRepository;
import com.jpmorgan.gbce.stockmarket.trade.model.Trade;

import java.util.List;

/**
 * Interface for Trade Repository
 */
public interface TradeRepository extends BaseRepository<String, List<Trade>> {
    Trade createTrade(String key, Trade value);

    List<Trade> tradesInlastMinutes(String key, int minutes);

    List<Trade> getAllTrades();

}

package com.jpmorgan.gbce.stockmarket.stock.service;

import com.jpmorgan.gbce.stockmarket.stock.model.Stock;

import java.math.BigDecimal;

/**
 * Interface for Stock Service
 */
public interface StockService {

    BigDecimal calculateDividendYield(String stockSymbol, BigDecimal stockPrice);

    BigDecimal calculatePERatio(String symbol, BigDecimal stockPrice);

    BigDecimal calculateVolumeWeightedPrice(String symbol);

    Stock getStockDetails(String stockSymbol);

}

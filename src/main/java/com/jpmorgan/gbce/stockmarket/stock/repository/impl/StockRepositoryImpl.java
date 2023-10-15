package com.jpmorgan.gbce.stockmarket.stock.repository.impl;

import com.jpmorgan.gbce.stockmarket.common.repository.impl.BaseRepositoryImpl;
import com.jpmorgan.gbce.stockmarket.core.database.StockMarketDBUtil;
import com.jpmorgan.gbce.stockmarket.stock.model.Stock;
import com.jpmorgan.gbce.stockmarket.stock.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Class for Stock Repository Implementation
 */
@Repository
public class StockRepositoryImpl extends BaseRepositoryImpl<String, Stock> implements StockRepository {

    @Autowired
    private StockMarketDBUtil stockMarketDBUtil;

    /**
     * Method gets stock market data
     *
     * @return
     */
    @Override
    protected Map<String, Stock> getStockMarketData() {
        return stockMarketDBUtil.getStockMarketData();
    }

}

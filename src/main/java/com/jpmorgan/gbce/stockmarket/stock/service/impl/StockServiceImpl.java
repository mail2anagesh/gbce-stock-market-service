package com.jpmorgan.gbce.stockmarket.stock.service.impl;

import com.jpmorgan.gbce.stockmarket.common.exception.RestRuntimeException;
import com.jpmorgan.gbce.stockmarket.common.model.ApiStatusCode;
import com.jpmorgan.gbce.stockmarket.core.utils.Constants;
import com.jpmorgan.gbce.stockmarket.stock.model.Stock;
import com.jpmorgan.gbce.stockmarket.stock.model.StockType;
import com.jpmorgan.gbce.stockmarket.stock.repository.StockRepository;
import com.jpmorgan.gbce.stockmarket.stock.service.StockService;
import com.jpmorgan.gbce.stockmarket.trade.model.Trade;
import com.jpmorgan.gbce.stockmarket.trade.service.TradeService;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Class provides methods for Stock Services
 */
@Service
public class StockServiceImpl implements StockService {

    private static final Logger log = LoggerFactory.getLogger(StockServiceImpl.class);
    @Value("${minutes.for.last.trades:15}")
    private Integer minutes;
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private TradeService tradeService;

    /**
     * Method to calculate PE Ratio
     *
     * @param stockSymbol
     * @param stockPrice
     * @return
     */
    @Override
    public BigDecimal calculatePERatio(String stockSymbol, BigDecimal stockPrice) {

        BigDecimal dividendYield = calculateDividendYield(stockSymbol, stockPrice);
        BigDecimal peRatio = stockPrice.divide(dividendYield, Constants.MATH_CONTEXT);
        log.debug("PE Ratio {}, Symbol {}, Stock Price {}", peRatio, stockSymbol, stockPrice);
        return peRatio;
    }

    /**
     * Method to calculate Dividend Yield
     *
     * @param stockSymbol
     * @param stockPrice
     * @return
     */
    @Override
    public BigDecimal calculateDividendYield(String stockSymbol, BigDecimal stockPrice) {

        Stock stock = getStockDetails(stockSymbol);
        BigDecimal dividendYield = (StockType.Common == stock.getType()) ?
                stock.getLastDividend().divide(stockPrice, Constants.MATH_CONTEXT) :
                stock.getFixedDividend().multiply(stock.getParValue()).divide(stockPrice.multiply(BigDecimal.valueOf(100)), Constants.MATH_CONTEXT);
        log.debug("Dividend Yield {}, Symbol {}, Stock Price {} ", dividendYield, stockSymbol, stockPrice);
        return dividendYield;
    }

    /**
     * Method to calculate Volume Weighted Price
     *
     * @param stockSymbol
     * @return
     */
    @Override
    public BigDecimal calculateVolumeWeightedPrice(String stockSymbol) {

        getStockDetails(stockSymbol);

        List<Trade> trades = tradeService.getTradesByLastMinutes(stockSymbol, minutes);
        BigDecimal volWeightedPrice = BigDecimal.ZERO;
        if (CollectionUtils.isEmpty(trades)) {
            log.warn("No Trades found");
            return volWeightedPrice;
        }

        BigDecimal sumTradedPrice = BigDecimal.ZERO;
        long sumQuantity = 0;
        for (Trade trade : trades) {
            final BigDecimal tradedPrice = trade.getTradedPrice();
            final long sharesQuantity = trade.getSharesQuantity();

            sumTradedPrice = sumTradedPrice.add(tradedPrice.multiply(BigDecimal.valueOf(sharesQuantity)));
            sumQuantity += sharesQuantity;
        }
        volWeightedPrice = sumTradedPrice.divide(BigDecimal.valueOf(sumQuantity), Constants.MATH_CONTEXT);

        log.debug("Volume Weighted Price {}, Symbol {}", volWeightedPrice, stockSymbol);
        return volWeightedPrice;
    }

    /**
     * Method to get the stock details
     *
     * @param stockSymbol
     * @return
     */
    @Override
    public Stock getStockDetails(String stockSymbol) {

        log.debug("Fetching Stock details: Symbol {} ", stockSymbol);
        Stock stock = stockRepository.retrieveByKey(stockSymbol);
        if (stock == null) {
            log.error("Stock details not found {} ", stockSymbol);
            throw new RestRuntimeException(ApiStatusCode.STOCK_NOT_FOUND, "Stock [" + stockSymbol + "] is invalid");
        }
        log.debug("Found Stock[{}] details: Symbol {} ", stock, stockSymbol);
        return stock;
    }

}

package com.jpmorgan.gbce.stockmarket.stock.controller;

import com.jpmorgan.gbce.stockmarket.common.exception.RestRuntimeException;
import com.jpmorgan.gbce.stockmarket.common.model.ApiStatusCode;
import com.jpmorgan.gbce.stockmarket.stock.model.DividendYieldResponse;
import com.jpmorgan.gbce.stockmarket.stock.model.PERatioResponse;
import com.jpmorgan.gbce.stockmarket.stock.model.VolumeWeightedResponse;
import com.jpmorgan.gbce.stockmarket.stock.service.StockService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * Stock Controller class to handle REST API calls
 */
@RestController
@RequestMapping("/stockmarket/v1")
@Api(tags = "Stock Service")
public class StockControllerV1 {

    private static final Logger log = LoggerFactory.getLogger(StockControllerV1.class);
    @Autowired
    private StockService stockService;

    /**
     * Method to handle Divident Yield REST API Endpoint
     *
     * @param stockPrice
     * @param symbol
     * @return
     */
    @ApiOperation(value = "Provides the Dividend Yield", notes = "Provides the Dividend Yield for given stock symbol and price")
    @RequestMapping(value = "/stock/{symbol}/dividendyield", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, name = "symbol", paramType = "path", value = "stock symbol")})
    public DividendYieldResponse calculateDividentYield(@RequestParam(required = true) BigDecimal stockPrice,
                                                        @PathVariable(required = true, name = "symbol") String symbol) {

        log.debug("Dividend Yield API request received: Symbol {}, Stock Price {} ", symbol, stockPrice);
        if (stockPrice.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RestRuntimeException(ApiStatusCode.BAD_REQUEST, "Stock Price should be positive");
        }
        BigDecimal dividendYield = stockService.calculateDividendYield(symbol, stockPrice);
        DividendYieldResponse response = new DividendYieldResponse.Builder(symbol, stockPrice, dividendYield).build();
        log.debug("Dividend Yield API request processed: {} ", response);
        return response;
    }

    /**
     * Method to handle PE Ratio REST API Endpoint
     *
     * @param stockPrice
     * @param symbol
     * @return
     */
    @ApiOperation(value = "Provides the PE Ratio", notes = "Provides the PE Ratio for given stock symbol and price")
    @RequestMapping(value = "/stock/{symbol}/peratio", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, name = "symbol", paramType = "path", value = "stock symbol")})
    public PERatioResponse calculatePERatio(@RequestParam(required = true) BigDecimal stockPrice,
                                            @PathVariable("symbol") String symbol) {

        log.debug("PE Ratio API request received: Symbol {}, Stock Price {}", symbol, stockPrice);
        if (stockPrice.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RestRuntimeException(ApiStatusCode.BAD_REQUEST, "Stock Price should be positive");
        }
        BigDecimal peRatio = stockService.calculatePERatio(symbol, stockPrice);
        PERatioResponse response = new PERatioResponse.Builder(symbol, stockPrice, peRatio).build();
        log.debug("PE Ratio API request processed: {}", response);
        return response;
    }

    /**
     * Method to handle Volume Weighted Price REST API Endpoint
     *
     * @param symbol
     * @return
     */
    @ApiOperation(value = "Provides the Volume Weighted Price", notes = "Provides the Volume Weighted Price for given stock symbol")
    @RequestMapping(value = "/stock/{symbol}/volumeweightedprice", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, name = "symbol", paramType = "path", value = "stock symbol")})
    public VolumeWeightedResponse calculateVolumeWeightedPrice(@PathVariable("symbol") String symbol) {

        log.debug("Volume Weighted Price API request received: Symbol {} ", symbol);
        BigDecimal volWeightedPrice = stockService.calculateVolumeWeightedPrice(symbol);
        VolumeWeightedResponse response = new VolumeWeightedResponse.Builder(symbol, volWeightedPrice).build();
        log.debug("Volume Weighted Price API request processed: {} ", response);
        return response;
    }
}

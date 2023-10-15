package com.jpmorgan.gbce.stockmarket.core.controller;

import com.jpmorgan.gbce.stockmarket.core.model.GBCEResponse;
import com.jpmorgan.gbce.stockmarket.core.service.StockMarketService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * Stock Market Controller class to handle REST API calls
 */
@RestController
@RequestMapping("/stockmarket/v1")
@Api(tags = "Stock Market Service")
public class StockMarketControllerV1 {

    private static final Logger log = LoggerFactory.getLogger(StockMarketControllerV1.class);
    @Autowired
    private StockMarketService stockMarketService;

    /**
     * Method to handle GBCE All Share Index REST API Endpoint
     *
     * @return
     */
    @ApiOperation(value = "Provides the GBCE All Share Index", notes = "Provides the GBCE All Share Index")
    @RequestMapping(value = "/gbce", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public GBCEResponse calculateGBCEIndex() {

        log.debug("GBCE Calculation API request received");
        BigDecimal gbce = stockMarketService.calculateGBCE();
        GBCEResponse response = new GBCEResponse.Builder(gbce).build();
        log.debug("GBCE Calculation API request processed: {} ", response);
        return response;
    }

}

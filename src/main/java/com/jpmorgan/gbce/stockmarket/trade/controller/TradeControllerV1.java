package com.jpmorgan.gbce.stockmarket.trade.controller;

import com.jpmorgan.gbce.stockmarket.common.model.ApiStatusCode;
import com.jpmorgan.gbce.stockmarket.common.model.RestApiResponse;
import com.jpmorgan.gbce.stockmarket.trade.model.StockTradeRequest;
import com.jpmorgan.gbce.stockmarket.trade.service.TradeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Trade Controller class to handle REST API calls
 */
@RestController
@RequestMapping("/stockmarket/v1")
@Api(tags = "Trade Service")
public class TradeControllerV1 {

    private static final Logger log = LoggerFactory.getLogger(TradeControllerV1.class);
    @Autowired
    private TradeService tradeService;

    /**
     * Method to handle creating trade entry REST API Endpoint
     *
     * @param tradeRequest
     * @return
     */
    @ApiOperation(value = "Creates the Trade entry", notes = "Creates the Trade entry")
    @RequestMapping(value = "/trade", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestApiResponse<Boolean> createTrade(@Valid @RequestBody StockTradeRequest tradeRequest) {

        log.debug("Trade creation API request received: {} ", tradeRequest);
        tradeService.createTrade(tradeRequest);
        log.debug("Trade creation API request processed: {} ", tradeRequest);
        return new RestApiResponse<>(ApiStatusCode.SUCCESS);
    }

}

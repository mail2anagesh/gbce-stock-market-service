package com.jpmorgan.gbce.stockmarket.core.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jpmorgan.gbce.stockmarket.common.model.ApiStatusCode;
import com.jpmorgan.gbce.stockmarket.trade.model.StockTradeRequest;
import com.jpmorgan.gbce.stockmarket.trade.model.TradeType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*
 * Class to test the StockMarketController functionality
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class StockMarketControllerV1Test {

    private static final ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private MockMvc mvc;

    @Test
    public void testGBCE() throws Exception {

        mockTrade("POP", 10l, new BigDecimal(10.0));
        mockTrade("POP", 20l, new BigDecimal(20.0));
        mockTrade("TEA", 20l, new BigDecimal(15.0));
        mockTrade("GIN", 20l, new BigDecimal(12.0));

        mvc.perform(MockMvcRequestBuilders.get("/stockmarket/v1/gbce").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.gbce").value(equalTo(13.774493079968597)));
    }

    private void mockTrade(String symbol, Long shareQuantity, BigDecimal tradePrice) throws Exception {

        StockTradeRequest request = new StockTradeRequest();
        request.setSharesQuantity(shareQuantity);
        request.setSymbol(symbol);
        request.setTradePrice(tradePrice);
        request.setType(TradeType.BUY);

        mvc.perform(
                post("/stockmarket/v1/trade")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(equalTo(ApiStatusCode.SUCCESS.getStatusCode())))
                .andExpect(jsonPath("$.message").value(equalTo(ApiStatusCode.SUCCESS.getMessage())));
    }
}

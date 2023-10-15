package com.jpmorgan.gbce.stockmarket.trade.controller;

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

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*
 * Class to test the TradeController functionality
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TradeControllerV1Test {

    private static final ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private MockMvc mvc;

    @Test
    public void testPostTradeWithInvalidSymbol() throws Exception {

        StockTradeRequest request = new StockTradeRequest();
        request.setSharesQuantity(10l);
        request.setSymbol("POP1");
        request.setTradePrice(new BigDecimal(10.0));
        request.setType(TradeType.BUY);

        mvc.perform(
                post("/stockmarket/v1/trade")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value(equalTo(ApiStatusCode.STOCK_NOT_FOUND.getStatusCode())))
                .andExpect(jsonPath("$.message").value(equalTo(ApiStatusCode.STOCK_NOT_FOUND.getMessage())));
    }


    @Test
    public void testPostTrade() throws Exception {

        StockTradeRequest request = new StockTradeRequest();
        request.setSharesQuantity(10l);
        request.setSymbol("POP");
        request.setTradePrice(new BigDecimal(10.0));
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

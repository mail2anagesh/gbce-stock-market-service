package com.jpmorgan.gbce.stockmarket.stock.controller;

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
 * Class to test the StockController functionality
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class StockControllerV1Test {

    private static final ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private MockMvc mvc;

    @Test
    public void testDividendYieldWithInvalidSymbol() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/stockmarket/v1/stock/abc/dividendyield?stockPrice=10.0").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value(equalTo(ApiStatusCode.STOCK_NOT_FOUND.getStatusCode())))
                .andExpect(jsonPath("$.message").value(equalTo(ApiStatusCode.STOCK_NOT_FOUND.getMessage())));
    }

    @Test
    public void testDividendYieldWithNoPrice() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/stockmarket/v1/stock/POP/dividendyield").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value(equalTo(ApiStatusCode.BAD_REQUEST.getStatusCode())))
                .andExpect(jsonPath("$.message").value(equalTo(ApiStatusCode.BAD_REQUEST.getMessage())));
    }

    @Test
    public void testPERatioWithInvalidSymbol() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/stockmarket/v1/stock/abc/peratio?stockPrice=10.0").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value(equalTo(ApiStatusCode.STOCK_NOT_FOUND.getStatusCode())))
                .andExpect(jsonPath("$.message").value(equalTo(ApiStatusCode.STOCK_NOT_FOUND.getMessage())));
    }

    @Test
    public void testPERatioWithNoPrice() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/stockmarket/v1/stock/POP/peratio").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value(equalTo(ApiStatusCode.BAD_REQUEST.getStatusCode())))
                .andExpect(jsonPath("$.message").value(equalTo(ApiStatusCode.BAD_REQUEST.getMessage())));
    }

    @Test
    public void testPERatio() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/stockmarket/v1/stock/POP/peratio?stockPrice=2").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.peRatio").value(equalTo(0.5)))
                .andExpect(jsonPath("$.symbol").value(equalTo("POP")));
    }

    @Test
    public void testVolWeightedWithInvalidSymbol() throws Exception {
        mockTrade("POP", 10l, new BigDecimal(10.0));
        mockTrade("POP", 20l, new BigDecimal(20.0));

        mvc.perform(MockMvcRequestBuilders.get("/stockmarket/v1/stock/POP1/volumeweightedprice").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value(equalTo(ApiStatusCode.STOCK_NOT_FOUND.getStatusCode())))
                .andExpect(jsonPath("$.message").value(equalTo(ApiStatusCode.STOCK_NOT_FOUND.getMessage())));
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

    @Test
    public void testDividendYield() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/stockmarket/v1/stock/POP/dividendyield?stockPrice=2").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.dividendYield").value(equalTo(4)))
                .andExpect(jsonPath("$.symbol").value(equalTo("POP")));
    }

    @Test
    public void testVolWeighted() throws Exception {
        mockTrade("POP", 10l, new BigDecimal(10.0));
        mockTrade("POP", 20l, new BigDecimal(20.0));

        mvc.perform(MockMvcRequestBuilders.get("/stockmarket/v1/stock/POP/volumeweightedprice").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.volWeighedPrice").value(equalTo(16.6667)))
                .andExpect(jsonPath("$.symbol").value(equalTo("POP")));
    }
}

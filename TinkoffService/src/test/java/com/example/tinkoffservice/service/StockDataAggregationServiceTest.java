package com.example.tinkoffservice.service;

import com.example.tinkoffservice.dto.FigiesDto;
import com.example.tinkoffservice.dto.StocksDto;
import com.example.tinkoffservice.dto.StocksPricesDto;
import com.example.tinkoffservice.dto.TickersDto;
import com.example.tinkoffservice.kafka.dto.MessageTinkoffDto;
import com.example.tinkoffservice.kafka.dto.StockDto;
import com.example.tinkoffservice.model.Stock;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.File;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class StockDataAggregationServiceTest {

    @MockBean
    StockService stockService;

    MessageTinkoffDto messageTinkoffDto;

    @BeforeEach
    @SneakyThrows
    void setUp() {

        messageTinkoffDto = new ObjectMapper()
                .readerFor(MessageTinkoffDto.class)
                .readValue(new File("src/test/resources/json/MessageTinkoffDto.json"));

        StocksDto info = new ObjectMapper()
                .readerFor(StocksDto.class)
                .readValue(new File("src/test/resources/json/StocksDto.json"));

        Mockito.doReturn(info)
                .when(stockService)
                .getStocksByTickers(any(TickersDto.class));

        Mockito.doReturn(new ObjectMapper()
                        .readerFor(StocksPricesDto.class)
                        .readValue(new File("src/test/resources/json/StocksPricesDto.json")))
                .when(stockService)
                .getPrices(any(FigiesDto.class));
    }

    @SneakyThrows
    @Test
    void aggregateData() {
        StockDataAggregationService stockDataAggregationService = new StockDataAggregationService(stockService);
        MessageTinkoffDto result = stockDataAggregationService.aggregateData(messageTinkoffDto);
        System.out.println(result);
        assertEquals(messageTinkoffDto.getStocksDto().get(0).getTicker() ,result.getStocksDto().get(0).getTicker());
    }
}
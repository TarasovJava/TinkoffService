package com.example.tinkoffservice.controller;

import com.example.tinkoffservice.dto.FigiesDto;
import com.example.tinkoffservice.dto.StocksDto;
import com.example.tinkoffservice.dto.StocksPricesDto;
import com.example.tinkoffservice.dto.TickersDto;
import com.example.tinkoffservice.model.Stock;
import com.example.tinkoffservice.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class StockController {
    private final StockService stockService;

    @GetMapping("/stocks/{ticker}")
    public Stock getStock(@PathVariable String ticker) {
        return stockService.getStockByTicker(ticker);
    }

    @PostMapping("/stocks/getStocksByTickers")
    public StocksDto getStocksByTickers(@RequestBody TickersDto tickersDto) {
        return stockService.getStocksByTickers(tickersDto);
    }

    @PostMapping("/prices")
    public StocksPricesDto getPrices(@RequestBody FigiesDto figiesDto) {
        return stockService.getPrices(figiesDto);
    }
}

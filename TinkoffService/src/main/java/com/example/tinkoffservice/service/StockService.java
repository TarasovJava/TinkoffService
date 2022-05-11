package com.example.tinkoffservice.service;

import com.example.tinkoffservice.dto.FigiesDto;
import com.example.tinkoffservice.dto.StocksDto;
import com.example.tinkoffservice.dto.StocksPricesDto;
import com.example.tinkoffservice.dto.TickersDto;
import com.example.tinkoffservice.model.Stock;
import org.springframework.stereotype.Service;

@Service
public interface StockService {
    Stock getStockByTicker(String ticker);

    StocksDto getStocksByTickers(TickersDto tickers);

    StocksPricesDto getPrices(FigiesDto figiesDto);
}

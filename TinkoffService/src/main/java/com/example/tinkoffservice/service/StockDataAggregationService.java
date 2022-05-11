package com.example.tinkoffservice.service;

import com.example.tinkoffservice.dto.*;
import com.example.tinkoffservice.kafka.dto.MessageTinkoffDto;
import com.example.tinkoffservice.kafka.dto.StockDto;
import com.example.tinkoffservice.model.Stock;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StockDataAggregationService {

    private final StockService tinkoffStockService;

    public MessageTinkoffDto aggregateData(MessageTinkoffDto messageTinkoffDto) {
        var info = tinkoffStockService.getStocksByTickers(new TickersDto()
                .setTickers(messageTinkoffDto.getStocksDto().stream()
                        .map(StockDto::getTicker)
                        .collect(Collectors.toList())));

        var prices = tinkoffStockService.getPrices(new FigiesDto().setFigies(info.getStocks()
                .stream()
                .map(Stock::getFigi)
                .collect(Collectors.toList())));
        messageTinkoffDto.getStocksDto().clear();
        messageTinkoffDto.setStocksDto(preparingResponse(info, prices));
        return messageTinkoffDto;
    }

    private List<StockDto> preparingResponse(StocksDto info,
                                             StocksPricesDto stocksPricesDto) {
        List<StockDto> stockDto = new ArrayList<>();
        for (StockPrice stockPrice : stocksPricesDto.getPrices()) {
            for (Stock stockInfo : info.getStocks()) {
                if (stockPrice.getFigi().equals(stockInfo.getFigi())) {
                    stockDto.add(new StockDto().setTicker(stockInfo.getTicker())
                            .setPrice(stockPrice.getPrice())
                            .setName(stockInfo.getName())
                            .setCurrency(stockInfo.getCurrency())
                            .setErrorCode("0"));
                }
            }
        }
        return stockDto;
    }
}

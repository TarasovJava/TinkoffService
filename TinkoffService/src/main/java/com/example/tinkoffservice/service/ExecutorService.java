package com.example.tinkoffservice.service;

import com.example.tinkoffservice.kafka.dto.MessageTinkoffDto;
import com.example.tinkoffservice.kafka.producer.Producer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExecutorService {

    private final Producer producer;
    private final StockDataAggregationService stockDataAggregationService;

    public void execute(MessageTinkoffDto messageTinkoffDto) {
        producer.send(stockDataAggregationService.aggregateData(messageTinkoffDto));
    }
}

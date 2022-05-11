package com.example.tinkoffservice.kafka.consumer;

import com.example.tinkoffservice.kafka.dto.MessageTinkoffDto;
import com.example.tinkoffservice.service.ExecutorService;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Consumer {

    private final ExecutorService executorService;

    @KafkaListener(topics = "in-tinkoff-stocks", groupId = "clientId")
    public void listenerTopic(ConsumerRecord<String, MessageTinkoffDto> record) {
        System.out.println(record);
        executorService.execute(record.value());
    }
}
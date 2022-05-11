package com.example.tinkoffservice.kafka.producer;

import com.example.tinkoffservice.kafka.dto.MessageTinkoffDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

@Component
public class Producer {

    @Autowired
    private KafkaTemplate<String, MessageTinkoffDto> kafkaTemplate;
    @Value("${tinkoff.kafka.producer.topic}")
    private String topic;
    public void send(MessageTinkoffDto message) {
        ListenableFuture<SendResult<String, MessageTinkoffDto>> sendMessage = kafkaTemplate.send(topic, message);
        kafkaTemplate.flush();
        System.out.println("Сообщение отправлено: " + sendMessage.isDone());
    }
}
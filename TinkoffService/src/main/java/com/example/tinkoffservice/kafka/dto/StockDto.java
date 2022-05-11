package com.example.tinkoffservice.kafka.dto;

import com.example.tinkoffservice.model.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString
public class StockDto {
    private String ticker;
    private String name;
    private double price;
    private Currency currency;
    private String errorCode;
    private String errorText;
}
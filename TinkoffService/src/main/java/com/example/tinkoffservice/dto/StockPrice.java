package com.example.tinkoffservice.dto;

import lombok.*;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class StockPrice {
    private String figi;
    private Double price;
}

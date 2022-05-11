package com.example.tinkoffservice.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Stock {
    String ticker;
    String figi;
    String name;
    String type;
    Currency currency;
    String source;

}

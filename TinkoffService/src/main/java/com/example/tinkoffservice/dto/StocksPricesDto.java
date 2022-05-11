package com.example.tinkoffservice.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class StocksPricesDto {
    private List<StockPrice> prices;
}

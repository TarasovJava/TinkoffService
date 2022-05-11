package com.example.tinkoffservice.kafka.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
public class MessageTinkoffDto {

    private Long requestId;

    private List<StockDto> stocksDto;

}

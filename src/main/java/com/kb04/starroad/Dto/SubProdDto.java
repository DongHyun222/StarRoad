package com.kb04.starroad.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubProdDto {
    private String name;
    private String attribute;
    private String explain;
    private int period;
    private int price;
    private char received;
}

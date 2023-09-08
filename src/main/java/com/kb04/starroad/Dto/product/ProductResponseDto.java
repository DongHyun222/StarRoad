package com.kb04.starroad.Dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDto {
    
//    private int no;
    private Character type;
    private String attribute;

    private String name;
    private String explain;

    private Double maxRate;
    private Integer maxRatePeriod;

    private String link;

}

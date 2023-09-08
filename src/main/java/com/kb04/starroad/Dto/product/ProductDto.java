package com.kb04.starroad.Dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private int no;
    private Character type;
    private String name;
    private String explain;
    private String attribute;
    private int minPeriod;
    private int maxPeriod;
    private int minPrice;
    private int maxPrice;
    private String link;
    private Double maxRate;
    private int maxRatePeriod;

}

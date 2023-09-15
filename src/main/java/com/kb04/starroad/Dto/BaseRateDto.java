package com.kb04.starroad.Dto;

import com.kb04.starroad.Dto.product.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseRateDto {

    private int no;
    private int minPeriod;
    private int maxPeriod;
    private Double rate;
    private ProductDto prod;

    private Double max_rate;

}

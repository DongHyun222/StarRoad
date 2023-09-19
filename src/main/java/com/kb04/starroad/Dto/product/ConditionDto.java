package com.kb04.starroad.Dto.product;

import com.kb04.starroad.Dto.product.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConditionDto {

    private int no;
    private ProductDto prod;
    private String conditionName;
    private Double rate;
    private Double maxRate;
}

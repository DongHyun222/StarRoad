package com.kb04.starroad.Dto.product;

import com.kb04.starroad.Entity.Product;
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

    private Integer maxPrice;

    private Double maxRate;
    private Integer maxRatePeriod;
    private Double maxConditionRate;

    private String link;

    public Product toEntity() {
        return Product.builder()
                .type(type)
                .attribute(attribute)
                .name(name)
                .explain(explain)
                .maxRate(maxRate)
                .maxRatePeriod(maxRatePeriod)
                .maxConditionRate(maxConditionRate)
                .link(link)
                .maxPrice(maxPrice)
                .build();
    }

}

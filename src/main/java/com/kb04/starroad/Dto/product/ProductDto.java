package com.kb04.starroad.Dto.product;

import com.kb04.starroad.Entity.Product;
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
    private Integer maxPrice;
    private String link;
    private Double maxRate;
    private Integer maxRatePeriod;
    private Double maxConditionRate;

    public Product toEntity() {
        return Product.builder()
                .no(no)
                .type(type)
                .name(name)
                .explain(explain)
                .attribute(attribute)
                .minPeriod(minPeriod)
                .maxPeriod(maxPeriod)
                .minPrice(minPrice)
                .maxPrice(maxPrice)
                .link(link)
                .maxRate(maxRate)
                .maxRatePeriod(maxRatePeriod)
                .maxConditionRate(maxConditionRate)
                .build();
    }

}

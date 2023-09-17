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
public class SubscriptionDto {

    private int no;
    private MemberDto member;
    private ProductDto prod;
    private int period;
    private int price;

}

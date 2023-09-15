package com.kb04.starroad.Entity;

import com.kb04.starroad.Dto.BaseRateDto;
import com.kb04.starroad.Dto.board.BoardResponseDto;
import com.kb04.starroad.Dto.product.ProductDto;
import com.kb04.starroad.Dto.product.ProductResponseDto;
import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "base_rate")
@SequenceGenerator(name = "base_rate_seq", sequenceName = "base_rate_seq", allocationSize = 1)
public class BaseRate {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "base_rate_seq")
    private int no;

    @Column(name = "min_period", nullable = false)
    private int minPeriod;

    @Column(name = "max_period", nullable = false)
    private int maxPeriod;

    @Column(nullable = false)
    private Double rate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prod_no", nullable = false)
    private Product prod;

    private Double max_rate;


    public BaseRateDto toBaseRateDto() {
        return BaseRateDto.builder()
                .no(no)
                .minPeriod(minPeriod)
                .maxPeriod(maxPeriod)
                .rate(rate)
                .prod(prod.toProductDto())
                .max_rate(max_rate)
                .build();
    }

    public ProductResponseDto toProductResponseDto() {
        return ProductResponseDto.builder()
                .baseRate(rate)
                .build();
    }
}

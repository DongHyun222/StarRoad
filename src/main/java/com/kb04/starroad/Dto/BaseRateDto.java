package com.kb04.starroad.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "base_rate")
@SequenceGenerator(name = "base_rate_seq", sequenceName = "base_rate_seq", allocationSize = 1)
public class BaseRateDto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "base_rate_seq")
    private int no;

    @Column(name = "min_period", nullable = false)
    private int minPeriod;

    @Column(name = "max_period", nullable = false)
    private int maxPeriod;

    @Column(nullable = false)
    private double rate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prod_no", nullable = false)
    private ProductDto productDto;

}

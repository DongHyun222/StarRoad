package com.kb04.starroad.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "condition")
public class ConditionDto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "condition_seq")
    @SequenceGenerator(name = "condition_seq", sequenceName = "CONDITION_SEQ", allocationSize = 1)
    @Column(name = "no")
    private int no;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prod_no", nullable = false)
    private ProductDto prodNo;

    @Column(name = "condition_name", nullable = false, length = 50)
    private String conditionName;

    @Column(name = "rate", nullable = false)
    private Double rate;

    @Column(name = "max_rate", nullable = false)
    private Double maxRate;
}

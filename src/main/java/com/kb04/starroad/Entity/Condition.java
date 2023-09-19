package com.kb04.starroad.Entity;

import com.kb04.starroad.Dto.product.ConditionDto;
import lombok.*;

import javax.persistence.*;


@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "condition")
public class Condition {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "condition_seq")
    @SequenceGenerator(name = "condition_seq", sequenceName = "CONDITION_SEQ", allocationSize = 1)
    @Column(name = "no")
    private int no;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prod_no", nullable = false)
    private Product prod;

    @Column(name = "condition_name", nullable = false, length = 100)
    private String conditionName;

    @Column(name = "rate", nullable = false)
    private Double rate;

    public ConditionDto toConditionDto() {
        return ConditionDto.builder()
                .no(no)
                .prod(prod.toProductDto())
                .conditionName(conditionName)
                .rate(rate)
                .build();

    }

}

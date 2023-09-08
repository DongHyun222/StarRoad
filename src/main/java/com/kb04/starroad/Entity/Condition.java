package com.kb04.starroad.Entity;

import lombok.*;

import javax.persistence.*;


@Getter
@Entity
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
    private Product prodNo;

    @Column(name = "condition_name", nullable = false, length = 50)
    private String conditionName;

    @Column(name = "rate", nullable = false)
    private Double rate;

    @Column(name = "max_rate", nullable = false)
    private Double maxRate;
}

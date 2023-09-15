package com.kb04.starroad.Entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "member_condition")
public class MemberCondition {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_condition_seq")
    @SequenceGenerator(name = "member_condition_seq", sequenceName = "MEMBER_CONDITION_SEQ", allocationSize = 1)
    @Column(name = "no")
    private int no;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "condition_no", nullable = false)
    private Condition condition;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_no", nullable = false)
    private Member member;
}

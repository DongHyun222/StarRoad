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
@Table(name = "member_condition")
public class MemberConditionDto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_condition_seq")
    @SequenceGenerator(name = "member_condition_seq", sequenceName = "MEMBER_CONDITION_SEQ", allocationSize = 1)
    @Column(name = "no")
    private int no;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "condition_no", nullable = false)
    private ConditionDto conditionNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_no", nullable = false)
    private MemberDto memberNo;
}

package com.kb04.starroad.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "policy_heart")
public class PolicyHeart {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "policy_heart_seq")
    @SequenceGenerator(name = "policy_heart_seq", sequenceName = "POLICY_HEART_SEQ", allocationSize = 1)
    @Column(name = "no")
    private int no;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_no")
    private Member member;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "policy_no")
    private Policy policy;
}

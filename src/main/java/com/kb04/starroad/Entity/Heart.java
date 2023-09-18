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
@Table(name = "heart")
public class Heart {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "heart_seq")
    @SequenceGenerator(name = "heart_seq", sequenceName = "HEART_SEQ", allocationSize = 1)
    @Column(name = "no")
    private int no;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_no")
    private Member member;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "board_no")
    private Board board;
}

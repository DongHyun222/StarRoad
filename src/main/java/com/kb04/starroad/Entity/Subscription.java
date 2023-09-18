package com.kb04.starroad.Entity;

import com.kb04.starroad.Dto.MemberDto;
import com.kb04.starroad.Dto.SubProdDto;
import com.kb04.starroad.Dto.SubscriptionDto;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "subscription_seq", sequenceName = "subscription_seq")
@Table(name = "subscription")
public class Subscription {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subscription_seq")
    private int no;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_no", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prod_no", nullable = false)
    private Product prod;

    @Column(nullable = false)
    private int period;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private char received = '0';    // 0: 받지 않음, 1: 받음

    public SubscriptionDto toSubscriptionDto() {
        return SubscriptionDto.builder()
                .no(no)
                .member(member.toMemberDto())
                .prod(prod.toProductDto())
                .period(period)
                .price(price)
                .received(received)
                .build();
    }

    public SubProdDto toSubProdDto() {
        return SubProdDto.builder()
                .name(prod.getName())
                .attribute(prod.getAttribute())
                .explain(prod.getExplain())
                .period(period)
                .price(price)
                .received(received)
                .build();
    }

    public void updateReceived(char status) {
        this.received = status;
    }
}

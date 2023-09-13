package com.kb04.starroad.Entity;

import com.kb04.starroad.Dto.MemberDto;
import com.kb04.starroad.Dto.SubscriptionDto;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "subscription_seq", sequenceName="subscription_seq")
@Table(name = "subscription")
public class Subscription {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="subscription_seq")
    private int no;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_no", nullable = false)
    private Member memberNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prod_no", nullable = false)
    private Product prodNo;

    @Column(nullable = false)
    private int period;

    @Column(nullable = false)
    private int price;

    public SubscriptionDto toSubscriptionDto() {
        return SubscriptionDto.builder()
                .no(no)
                .memberNo(memberNo.toMemberDto())
                .prodNo(prodNo.toProductDto())
                .period(period)
                .price(price)
                .build();
    }
}

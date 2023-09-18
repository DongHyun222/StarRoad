package com.kb04.starroad.Entity;

import com.kb04.starroad.Dto.PaymentLogDto;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "payment_log_seq", sequenceName="payment_log_seq")
@Table(name = "payment_log")
public class PaymentLog {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="payment_log_seq")
    private int no;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subscription_no", nullable = false)
    private Subscription subscription;

    @Column(nullable = false)
    private Date paymentDate;

    public PaymentLogDto toPaymentLogDto() {
        return PaymentLogDto.builder()
                .no(no)
                .subscription(subscription.toSubscriptionDto())
                .paymentDate(paymentDate)
                .build();
    }
}

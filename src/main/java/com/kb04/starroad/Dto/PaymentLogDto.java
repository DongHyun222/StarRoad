package com.kb04.starroad.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "payment_log_seq", sequenceName="payment_log_seq")
@Table(name = "payment_log")
public class PaymentLogDto {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="payment_log_seq")
    private int no;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subscription_no", nullable = false)
    private SubscriptionDto subscriptionNo;

    @Column(nullable = false)
    private Date paymentDate;
}

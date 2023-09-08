package com.kb04.starroad.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentLogDto {

    private int no;
    private SubscriptionDto subscriptionNo;
    private Date paymentDate;
}

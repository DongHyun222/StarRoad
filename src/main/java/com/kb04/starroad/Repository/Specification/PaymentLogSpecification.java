package com.kb04.starroad.Repository.Specification;

import com.kb04.starroad.Entity.PaymentLog;
import org.springframework.data.jpa.domain.Specification;

public class PaymentLogSpecification {
    public static Specification<PaymentLog> getPayLogsBySubNo(int sub_no) {
        return ((root, query, criteriaBuilder) -> {
            query.orderBy(criteriaBuilder.asc(root.get("paymentDate")));
            return criteriaBuilder.equal(root.get("subscription").get("no"), sub_no);
        });
    }
}

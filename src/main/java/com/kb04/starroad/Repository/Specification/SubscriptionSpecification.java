package com.kb04.starroad.Repository.Specification;

import com.kb04.starroad.Entity.PaymentLog;
import com.kb04.starroad.Entity.Subscription;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class SubscriptionSpecification {
    public static Specification<Subscription> getSubscriptions(int userNo) {
        return ((root, query, criteriaBuilder) -> {
            query.orderBy(criteriaBuilder.desc(root.get("no")));
            return criteriaBuilder.equal(root.get("memberNo"), userNo);
        });
    }
}

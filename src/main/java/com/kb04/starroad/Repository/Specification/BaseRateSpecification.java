package com.kb04.starroad.Repository.Specification;

import com.kb04.starroad.Entity.BaseRate;
import com.kb04.starroad.Entity.Product;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class BaseRateSpecification {

    public static Specification<BaseRate> lessThanMinPeriod(int period) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.lessThan(root.get("minPeriod"), period);
    }

    public static Specification<BaseRate> maxRateSpecification(int period) {
        return (root, query, criteriaBuilder) -> {
            Subquery<Double> subquery = query.subquery(Double.class);
            Root<BaseRate> subRoot = subquery.from(BaseRate.class);
            Join<BaseRate, Product> subProductJoin = subRoot.join("prod");
            subquery.select(criteriaBuilder.max(subRoot.get("rate")))
                    .where(criteriaBuilder.and(
                            criteriaBuilder.equal(subProductJoin.get("no"), root.get("prod").get("no")),
                            criteriaBuilder.lessThanOrEqualTo(subRoot.get("minPeriod"), period)
                    ))
                    .groupBy(subRoot.get("prod"));

            return criteriaBuilder.equal(root.get("rate"), subquery);
        };
    }

}

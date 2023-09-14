package com.kb04.starroad.Repository.Specification;

import com.kb04.starroad.Dto.product.ProductResponseDto;
import com.kb04.starroad.Entity.Product;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Order;
import java.util.ArrayList;
import java.util.List;

public class ProductSpecification {

    public static Specification<Product> containsName(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"),"%" + name + "%");
    }
    public static Specification<Product> lessThanOrEqualToMinPeriod(int period) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("minPeriod"), period);
    }
    public static Specification<Product> equalsType(Character type) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("type"), type);
    }

    public static Specification<Product> lessThanOrEqualToMinPrice(Double price) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("minPrice"), price);
    }

    public static Specification<Product> greaterThanOrEqualToMaxPrice(Double price) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("maxPrice"), price);
    }

    public static Specification<Product> orderByMaxRateDescMaxRatePeriodDesc(Specification<Product> spec) {
        return (root, query, builder) -> {
            query.orderBy(builder.desc(root.get("maxRateTimesPeriod")));
            return spec.toPredicate(root, query, builder);
        };
    }
}

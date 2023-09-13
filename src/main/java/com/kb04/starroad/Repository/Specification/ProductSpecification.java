package com.kb04.starroad.Repository.Specification;

import com.kb04.starroad.Dto.product.ProductResponseDto;
import com.kb04.starroad.Entity.Product;
import com.kb04.starroad.Entity.Subscription;
import org.springframework.data.jpa.domain.Specification;

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

    public static Specification<Subscription> getProdInfo(String sub_name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("prodName"), sub_name);
    }
}

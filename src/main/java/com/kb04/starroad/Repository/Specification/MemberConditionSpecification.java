package com.kb04.starroad.Repository.Specification;

import com.kb04.starroad.Entity.Member;
import com.kb04.starroad.Entity.MemberCondition;
import com.kb04.starroad.Entity.Product;
import org.springframework.data.jpa.domain.Specification;

public class MemberConditionSpecification {

    public static Specification<MemberCondition> equalsMemberNo(Member member) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("member"), member);
    }
}

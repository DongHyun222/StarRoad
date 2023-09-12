package com.kb04.starroad.Repository.Specification;

import com.kb04.starroad.Entity.Policy;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import java.util.*;


public class PolicySpecification {
    public static Specification<Policy> searchPolicyWithMultiConditions(Map<String, ?> conditions){
        return(((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            for(String key: conditions.keySet()) {
                switch (key) {
                    case "location":
                        predicates.add(criteriaBuilder.equal(root.get(key), String.valueOf(conditions.get(key))));
                        break;
                    case "keyword":
                        predicates.add(criteriaBuilder.like(root.get("name"), '%' + String.valueOf(conditions.get(key)) + '%'));
                        break;
                    case "tag":

                        predicates.add(criteriaBuilder.and(root.get("tag").in((Collection<?>) conditions.get(key))));
                        break;
                }
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }));
    }


}

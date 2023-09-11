package com.kb04.starroad.Repository;

import com.kb04.starroad.Entity.Board;
import org.springframework.data.jpa.domain.Specification;

public class BoardSpecification {
    public static Specification<Board> writtenByUser(int userNo) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("member").get("no"), userNo));
    }
}

package com.kb04.starroad.Repository;

import com.kb04.starroad.Entity.Comment;
import org.springframework.data.jpa.domain.Specification;

public class CommentSpecification {
    public static Specification<Comment> writtenByUser(int userNo) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("member").get("no"), userNo));
    }
}
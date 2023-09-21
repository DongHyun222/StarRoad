package com.kb04.starroad.Repository.Specification;

import com.kb04.starroad.Entity.Board;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class BoardSpecification {
    public static Specification<Board> writtenByUser(int userNo) {
        return ((root, query, criteriaBuilder) -> {
            query.orderBy(criteriaBuilder.desc(root.get("regdate")));
            return criteriaBuilder.equal(root.get("member").get("no"), userNo);
        });
    }

    /**
     * 게시판에서 게시글 상태가 Y인 게시글 날짜 순으로 정렬하여 반환
     * @param type 게시판 타입
     * @param status  게시글 상태
     */
    public static Specification<Board> searchBoardByStatusAndType(String type, Character status) {
        return ((root, query, criteriaBuilder) -> {
            query.orderBy(criteriaBuilder.desc(root.get("regdate")));
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(root.get("status"), status));
            predicates.add(criteriaBuilder.equal(root.get("type"), type));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }
}

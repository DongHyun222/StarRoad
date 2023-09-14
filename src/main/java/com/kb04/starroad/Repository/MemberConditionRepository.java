package com.kb04.starroad.Repository;

import com.kb04.starroad.Entity.Member;
import com.kb04.starroad.Entity.MemberCondition;
import com.kb04.starroad.Entity.Product;
import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberConditionRepository extends JpaRepository<MemberCondition, Integer>, JpaSpecificationExecutor<MemberCondition> {

    List<MemberCondition> findAll(Specification<MemberCondition> spec);
}

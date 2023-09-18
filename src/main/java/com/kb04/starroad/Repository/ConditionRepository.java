package com.kb04.starroad.Repository;

import com.kb04.starroad.Entity.Condition;
import com.kb04.starroad.Entity.MemberCondition;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConditionRepository extends JpaRepository<MemberCondition, Integer>, JpaSpecificationExecutor<Condition> {
    List<Condition> findAll(Specification<Condition> spec);
}

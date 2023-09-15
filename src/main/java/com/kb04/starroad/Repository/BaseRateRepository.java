package com.kb04.starroad.Repository;

import com.kb04.starroad.Entity.BaseRate;
import com.kb04.starroad.Entity.Board;
import com.kb04.starroad.Entity.Member;
import com.kb04.starroad.Entity.Product;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface BaseRateRepository extends JpaRepository<BaseRate, Integer> {

    List<BaseRate> findAll(Specification<BaseRate> spec);
}

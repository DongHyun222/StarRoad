package com.kb04.starroad.Repository;

import com.kb04.starroad.Entity.Policy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PolicyRepository extends JpaRepository<Policy, Integer>{

    @Query(nativeQuery = true, value = "select * from policy p where p.tag in :tagList")
    List<Policy> findByTagInRequest(@Param("tagList") List<String> tagList);

    @Query(nativeQuery = true, value = "select * from policy p where p.name like '%:keyword%'")
    List<Policy> findByName(@Param("keyword") String keyword);

    List<Policy> findByLocation(String location);
}

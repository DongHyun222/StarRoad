package com.kb04.starroad.Repository;

import com.kb04.starroad.Entity.PolicyHeart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PolicyHeartRepository extends JpaRepository<PolicyHeart, Integer> {

    @Query(nativeQuery = true, value = "select * from policy_heart where member_no = :no")
    List<PolicyHeart> findAllByMemberNo(@Param("no") int no);

    @Query(nativeQuery = true, value = "select * from policy_heart where member_no = :memberNo and policy_no = :policyNo")
    PolicyHeart findByMemberNoAndPolicyNo(@Param("memberNo") int memberNo, @Param("policyNo") int policyNo);
}

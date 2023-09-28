package com.kb04.starroad.Repository;

import com.kb04.starroad.Entity.PolicyHeart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PolicyHeartRepository extends JpaRepository<PolicyHeart, Integer> {

    List<PolicyHeart> findAllByMemberNo(int no);
}

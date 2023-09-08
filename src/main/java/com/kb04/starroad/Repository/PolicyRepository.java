package com.kb04.starroad.Repository;

import com.kb04.starroad.Dto.policy.PolicyDto;
import com.kb04.starroad.Entity.Policy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PolicyRepository extends JpaRepository<Policy, Integer>{


}

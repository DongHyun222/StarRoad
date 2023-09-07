package com.kb04.starroad.Repository;

import com.kb04.starroad.Dto.PolicyDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PolicyRepository extends JpaRepository<PolicyDto, Integer>{

    Page<PolicyDto> findAll(Pageable pageable);
}

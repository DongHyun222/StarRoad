package com.kb04.starroad.Repository;

import com.kb04.starroad.Entity.PaymentLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentLogRepository extends JpaRepository<PaymentLog, Integer>, JpaSpecificationExecutor<PaymentLog> {
}

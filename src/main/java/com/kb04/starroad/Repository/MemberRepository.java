package com.kb04.starroad.Repository;

import com.kb04.starroad.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    Member findByNo(int no);

    @Query(value = "SELECT SUM(PRICE) FROM PRODUCT INNER JOIN SUBSCRIPTION ON PRODUCT.NO = SUBSCRIPTION.PROD_NO INNER JOIN PAYMENT_LOG ON SUBSCRIPTION.NO = PAYMENT_LOG.SUBSCRIPTION_NO WHERE MEMBER_NO = :no AND PRODUCT.TYPE='S'", nativeQuery = true)
    Integer getSavings(@Param("no") int no);

    @Query(value = "SELECT SUM(PRICE) FROM PRODUCT INNER JOIN SUBSCRIPTION ON PRODUCT.NO = SUBSCRIPTION.PROD_NO INNER JOIN PAYMENT_LOG ON SUBSCRIPTION.NO = PAYMENT_LOG.SUBSCRIPTION_NO WHERE MEMBER_NO = :no AND PRODUCT.TYPE='D'", nativeQuery = true)
    Integer getDeposit(@Param("no") int no);
}
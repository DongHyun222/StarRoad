package com.kb04.starroad.Repository;

import com.kb04.starroad.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    Optional<Member> findByIdAndPassword(String id, String password);

    Member findByNo(int no);

    @Query(value = "SELECT SUM(PRICE) FROM PRODUCT INNER JOIN SUBSCRIPTION ON PRODUCT.NO = SUBSCRIPTION.PROD_NO INNER JOIN PAYMENT_LOG ON SUBSCRIPTION.NO = PAYMENT_LOG.SUBSCRIPTION_NO WHERE MEMBER_NO = :no AND PRODUCT.TYPE='S'", nativeQuery = true)
    Integer getSavings(@Param("no") int no);

    @Query(value = "SELECT SUM(PRICE) FROM PRODUCT INNER JOIN SUBSCRIPTION ON PRODUCT.NO = SUBSCRIPTION.PROD_NO INNER JOIN PAYMENT_LOG ON SUBSCRIPTION.NO = PAYMENT_LOG.SUBSCRIPTION_NO WHERE MEMBER_NO = :no AND PRODUCT.TYPE='D'", nativeQuery = true)
    Integer getDeposit(@Param("no") int no);

    Member findById(String id);



    // Member 엔티티의 필드를 이용하여 데이터베이스 업데이트를 수행하는 쿼리 메소드
    @Modifying
    @Transactional
    @Query("UPDATE Member m SET m.phone = :phone, m.email = :email, m.address = :address, " +
            "m.job = :job, m.salary = :salary, m.purpose = :purpose, m.source = :source, " +
            "m.goal = :goal WHERE m.no = :no")
    void updateMember(@Param("no") int no,
                      @Param("phone") String phone,
                      @Param("email") String email,
                      @Param("address") String address,
                      @Param("job") String job,
                      @Param("salary") int salary,
                      @Param("purpose") String purpose,
                      @Param("source") String source,
                      @Param("goal") int goal);

}
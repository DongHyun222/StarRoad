package com.kb04.starroad.Repository;

import com.kb04.starroad.Entity.Heart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
//asd0103996
@Repository
public interface HeartRepository extends JpaRepository<Heart, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM HEART WHERE MEMBER_NO = :memberNo AND board_no = :boardNo" )
    Optional<Heart> findByMemberNoAndBoardNo(@Param("memberNo") int memberNo, @Param("boardNo")int boardNo);
}

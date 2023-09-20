package com.kb04.starroad.Repository;

import com.kb04.starroad.Entity.Board;
import com.kb04.starroad.Entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer>, JpaSpecificationExecutor<Board> {
    Page<Board> findByTypeAndStatus(String type,Character status, Pageable pageable);


    Page<Board> findAllByStatusOrderByLikesDesc(Character status,Pageable pageable);



    List<Board> findAllByMemberNoOrderByRegdate(Member memberNo);

    Optional<Board> findById(Integer no);
    Page<Board> findAllByTypeAndStatusOrderByRegdateDesc(String f, char y, Pageable pageable);

    Page<Board> findAllByStatusAndLikesGreaterThanEqualAndRegdateAfterOrderByLikesDesc(
            char status, int minLikes, Date oneWeekAgo, Pageable pageable
    );
    Board findByNo(int no);

}

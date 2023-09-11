package com.kb04.starroad.Repository;

import com.kb04.starroad.Entity.Board;
import com.kb04.starroad.Entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {
    Page<Board> findByType(String type, Pageable pageable);
    Page<Board> findAllByOrderByLikesDesc(Pageable pageable);
    List<Board> findAllByMemberNoOrderByRegdate(Member memberNo);
}

package com.kb04.starroad.Repository;

import com.kb04.starroad.Entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {
    Page<Board> findByType(String type, Pageable pageable);
}

package com.kb04.starroad.Repository;

import com.kb04.starroad.Dto.BoardDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<BoardDto, Integer> {

}

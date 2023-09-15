package com.kb04.starroad.Repository;

import com.kb04.starroad.Dto.MemberDto;
import com.kb04.starroad.Dto.board.CommentDto;
import com.kb04.starroad.Entity.Board;
import com.kb04.starroad.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer>, JpaSpecificationExecutor<Comment> {

    List<CommentDto> findByBoard(Board board);
    Optional<Comment> findByNo(Comment comment);
    void deleteByNo(int commentNo);
}

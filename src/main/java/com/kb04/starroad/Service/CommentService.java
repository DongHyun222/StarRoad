package com.kb04.starroad.Service;

import com.kb04.starroad.Dto.board.CommentDto;
import com.kb04.starroad.Entity.Board;
import com.kb04.starroad.Entity.Comment;
import com.kb04.starroad.Repository.BoardRepository;
import com.kb04.starroad.Repository.CommentRepository;
import com.kb04.starroad.Repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private MemberRepository memberRepository;

    public List<CommentDto> findByBoard(Board board) {

        return commentRepository.findByBoard(board);
    }


    @Transactional
    public int createComment(String content, int boardNo) {

        CommentDto newComment = new CommentDto();

        newComment.setContent(content);
        newComment.setRegdate(new java.util.Date());
//      newComment.setMember(memberRepository.findByNo(1));
        newComment.setBoard(boardRepository.findByNo(boardNo));;

        Comment comment = newComment.toEntity();

        commentRepository.save(comment);
        return comment.getBoard().getNo();
    }

}

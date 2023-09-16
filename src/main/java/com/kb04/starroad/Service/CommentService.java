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
import java.util.Optional;


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
//      newComment.setMember(memberRepository.findByNo(1)); 로그인된 사용자 정보 설정
        newComment.setBoard(boardRepository.findByNo(boardNo));;

        Comment comment = newComment.toEntity();

        commentRepository.save(comment);
        return comment.getBoard().getNo();
    }

    public CommentDto getCommentById(int commentNo) {

        Comment comment = commentRepository.findById(commentNo).orElseThrow(() -> new IllegalArgumentException("해당 댓글이 없습니다"));
        return comment.toCommentDto();
    }

    @Transactional
    public void updateComment(CommentDto commentDto) {

        Optional<Comment> optionalcomment = commentRepository.findByNo(commentDto.getNo());

        Comment comment2 = optionalcomment.get();
        comment2.update(commentDto.getContent());
    }

    @Transactional
    public void deleteComment(int commentNo) {
        commentRepository.deleteByNo(commentNo);
    }

    public Optional<Comment> findByNo(int commentNo) {
        return commentRepository.findById(commentNo);
    }

}

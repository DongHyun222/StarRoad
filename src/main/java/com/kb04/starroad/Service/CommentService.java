package com.kb04.starroad.Service;

import com.kb04.starroad.Dto.board.BoardRequestDto;
import com.kb04.starroad.Dto.board.BoardResponseDto;
import com.kb04.starroad.Dto.board.CommentDto;
import com.kb04.starroad.Entity.Board;
import com.kb04.starroad.Entity.Comment;
import com.kb04.starroad.Entity.Member;
import com.kb04.starroad.Repository.BoardRepository;
import com.kb04.starroad.Repository.CommentRepository;
import com.kb04.starroad.Repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
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

        return commentRepository.findByBoardOrderByRegdate(board);
    }



    @Transactional
    public int createComment(String content, int boardNo, int currentUserNo) {

        CommentDto newComment = new CommentDto();

        newComment.setContent(content);
        newComment.setRegdate(new java.util.Date());
        newComment.setMember(memberRepository.findByNo(currentUserNo));
        newComment.setBoard(boardRepository.findByNo(boardNo));;

        Comment comment = newComment.toEntity();

        commentRepository.save(comment);
        return comment.getBoard().getNo();
    }

    public CommentDto getCommentById(int commentNo) {

        Comment comment = commentRepository.findById(commentNo).orElseThrow(() -> new IllegalArgumentException("해당 댓글이 없습니다"));
        return comment.toCommentDto();
    }
    public CommentDto getUpdateComment(int no) {
        Optional<Comment> commentOptional = commentRepository.findByNo(no);
        if (commentOptional.isPresent()) {
            return commentOptional.get().toCommentDto();
        } else {
            // 여기에서 적절한 예외를 던지거나 null을 반환할 수 있습니다.
            throw new NoSuchElementException("No Comment found with given id");
        }
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

    @Transactional
    public void increaseCommentCount(int boardNo) {

        Optional<Board> optionalBoard = boardRepository.findById(boardNo);
        if (optionalBoard != null) {
            Board board = optionalBoard.get();
            int currentCount = board.getCommentNum();
            board.setCommentNum(currentCount + 1);
            System.out.println("board.getCommentNum(): " + board.getCommentNum());
            boardRepository.save(board);
            System.out.println("board.getCommentNum(): " + board.getCommentNum());
        }
    }

    public boolean canUpdate(int no, String currentUserId) {
        Optional<Comment> commentOptional = findByNo(no);

        if(commentOptional.isPresent()) {
            Comment comment = commentOptional.get();
            Member writer = comment.getMember();

            if (writer != null) {
                String writerId = writer.getId();
                return currentUserId != null && currentUserId.equals(writerId);
            }
        }
        return false;
    }
}

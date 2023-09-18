package com.kb04.starroad.Controller;

import com.kb04.starroad.Dto.MemberDto;
import com.kb04.starroad.Dto.board.BoardResponseDto;
import com.kb04.starroad.Dto.board.CommentDto;
import com.kb04.starroad.Entity.Board;
import com.kb04.starroad.Entity.Member;
import com.kb04.starroad.Service.BoardService2;
import com.kb04.starroad.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@RestController
public class    BoardController3 {

    @Autowired
    private BoardService2 boardService2;

    @Autowired
    private CommentService commentService;


    @GetMapping("/starroad/board/delete")
    public ModelAndView deleteBoard(@RequestParam Integer no, HttpSession session) {
        ModelAndView mav = new ModelAndView();

        if (session.getAttribute("currentUser") == null) {
            mav = new ModelAndView("redirect:/starroad/login");
            mav.addObject("message", "로그인 후에 댓글을 작성할 수 있습니다.");
            return mav;
        }
        
        MemberDto currentUser = (MemberDto) session.getAttribute("currentUser");
        String currentUserId = currentUser.getId();


        if (boardService2.canDelete(no, currentUserId)) {
            // 현재 사용자가 게시글 삭제 가능한 경우
            boardService2.deleteBoard(no);
            mav.setViewName("redirect:/starroad/board/main");

        } else{
            // 현재 사용자가 게시글 삭제 불가
            // 능한 경우 or 게시글 존재하지 않는 경우
            mav.setViewName("board/deleteError");
        }

        return mav;
    }
    @GetMapping("/starroad/board/detail")
    public ModelAndView getBoardDetail(@RequestParam("no") Integer no) {

        ModelAndView mav = new ModelAndView("board/detail");

        Optional<Board> boardOptional = boardService2.findById(no);

        if (boardOptional.isPresent()) {
            Board board = boardOptional.get();

            List<CommentDto> comments = commentService.findByBoard(board);

            BoardResponseDto boardResponseDto = board.toBoardResponseDto();
            String memberId = board.getMember().getId();  // 'getId()'는 실제 회원 ID를 반환하는 메서드로 변경해야 합니다.
            boardResponseDto.setMemberId(memberId);  // 'setMemberId()'는 DTO에서 회원 ID를 설정하는 메서드입니다.
           //boardResponseDto.setCommentNum(board.getCommentNum()); // commentNum 수정
            boardResponseDto.setComments(comments);

            // 이미지를 Base64로 인코딩하여 DTO에 추가
            if (board.getImage() != null) {
                byte[] imageBytes = board.getImage();
                String imageBase64 = Base64.getEncoder().encodeToString(imageBytes);
                boardResponseDto.setImageBase64(imageBase64);
            }

            if (boardResponseDto.getComments() == null || boardResponseDto.getComments().isEmpty()) {
                mav.addObject("noComments", true);
            }

            mav.addObject("board", boardResponseDto);
        } else {
            mav.addObject("error", "게시글을 찾을 수 없습니다.");
        }

        return mav;
    }

    @PostMapping("/starroad/board/like")
    public ModelAndView handleLike(@RequestParam("board") int boardNo,
                                   HttpSession session) {

        MemberDto memberDto = (MemberDto) session.getAttribute("currentUser");
        System.out.println("debug = " + memberDto.getId());
        
        if (memberDto.getId() == null ){

            ModelAndView mav = new ModelAndView("redirect:/starroad/login");
            mav.addObject("message", "로그인 후에 게시글을 작성할 수 있습니다.");
            return mav;

        } else if (!boardService2.hasLiked(boardNo,memberDto.getId())) {

            ModelAndView mav= new ModelAndView("redirect:/starroad/board/detail?no=" + boardNo);
            mav.addObject("message", "로그인 후에 좋아요를 누르거나 이미 좋아요한 게시글입니다.");
            return mav;
        }

        else {

            boardService2.increaseLikes(boardNo, memberDto.getId());
            ModelAndView mav= new ModelAndView("redirect:/starroad/board/detail?no=" + boardNo);
            return mav;
        }
    }


}
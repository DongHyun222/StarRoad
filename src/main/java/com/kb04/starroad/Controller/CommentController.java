package com.kb04.starroad.Controller;

import com.kb04.starroad.Dto.board.BoardResponseDto;
import com.kb04.starroad.Dto.board.CommentDto;
import com.kb04.starroad.Entity.Board;
import com.kb04.starroad.Entity.Comment;
import com.kb04.starroad.Service.BoardService2;
import com.kb04.starroad.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Optional;


@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private BoardService2 boardService;

    @PostMapping("/starroad/comment")
    public ModelAndView createComment(@RequestParam("content") String content,
                                      @RequestParam("board") int boardNo, HttpSession session) {

        if (session.getAttribute("currentUser") == null) {
            ModelAndView mav = new ModelAndView("redirect:/starroad/login");
            mav.addObject("message", "로그인 후에 댓글을 작성할 수 있습니다.");
            return mav;
        }

        int number = commentService.createComment(content, boardNo);
        ModelAndView mav = new ModelAndView("redirect:/starroad/board/detail?no=" + number);
        return mav;
    }

    @GetMapping("/starroad/comment")
    public ModelAndView getCommentByBoard(@RequestParam("no") int commentNo) {
        CommentDto commentDto = commentService.getCommentById(commentNo);
        ModelAndView mav = new ModelAndView("board/detail");
        mav.addObject("comment", commentDto);
        return mav;
    }

    @GetMapping("/starroad/comment/update")
    public ModelAndView updateComment(@RequestParam("no") int commentNo) {

        ModelAndView mav = new ModelAndView("board/comment/update");

        Optional<Comment> commentOptional = commentService.findByNo(commentNo);

        if(commentOptional.isPresent()) {
            Comment comment = commentOptional.get();
            CommentDto commentDto = comment.toCommentDto();
            mav.addObject("comment", commentDto);
        } else {
            mav.addObject("errorMessage", "해당하는 댓글이 존재하지 않습니다.");
        }

        return mav;
    }
    @PostMapping("/starroad/comment/doUpdate")
    public ModelAndView processUpdateComment(@RequestParam CommentDto commentDto) {

        commentService.updateComment(commentDto);
        ModelAndView mav = new ModelAndView("redirect:/starroad/board/main");
        return mav;
    }
    @PostMapping("/starroad/comment/delete")
    public ModelAndView deleteComment(@RequestParam("no") int commentNo) {

        commentService.deleteComment(commentNo);
        ModelAndView mav = new ModelAndView("redirect:/starroad/board/main");
        return mav;
    }

}

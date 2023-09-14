package com.kb04.starroad.Controller;

import com.kb04.starroad.Dto.board.CommentDto;
import com.kb04.starroad.Service.BoardService2;
import com.kb04.starroad.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private BoardService2 boardService;

    @PostMapping("/starroad/comment")
    public ModelAndView createComment(@RequestParam("content") String content, @RequestParam("board") int boardNo) {


        int number = commentService.createComment(content, boardNo);
        ModelAndView mav = new ModelAndView("redirect:/starroad/board/detail?no=" + number);
        return mav;
    }
//   @PutMapping("/starroad/comment/no")
//   public ModelAndView updateComent(@Param)
}

package com.kb04.starroad.Controller;

import com.kb04.starroad.Dto.board.CommentDto;
import com.kb04.starroad.Entity.Comment;
import com.kb04.starroad.Service.BoardService;
import com.kb04.starroad.Service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Api(tags = {"댓글 API"})
@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private BoardService boardService;

    @ApiOperation(value = "댓글 생성", notes = "댓글을 생성 할 수 있다")
    @PostMapping("/starroad/comment")
    public ModelAndView createComment(@ApiParam(value ="댓글 내용") @RequestParam("content") String content,
                                      @ApiParam(value = "게시글 번호", example = "1") @RequestParam("board") int boardNo,
                                      @ApiIgnore HttpSession session) {

        if (session.getAttribute("currentUser") == null) {
            ModelAndView mav = new ModelAndView("redirect:/starroad/login");
            mav.addObject("message", "로그인 후에 댓글을 작성할 수 있습니다.");
            return mav;
        }

        int number = commentService.createComment(content, boardNo);

        commentService.increaseCommentCount(boardNo);

        ModelAndView mav = new ModelAndView("redirect:/starroad/board/detail?no=" + number);
        return mav;
    }

    // 조회
    @ApiOperation(value = "댓글 조회", notes = "댓글을 조회 할 수 있다")
    @GetMapping("/starroad/comment")
    public ModelAndView getCommentByBoard(@ApiParam(value = "댓글 번호", example = "1") @RequestParam("no") int commentNo,
                                          @ApiIgnore HttpSession session) {

        if (session.getAttribute("currentUser") == null) {
            ModelAndView mav = new ModelAndView("redirect:/starroad/login");
            mav.addObject("message", "로그인 후에 댓글을 작성할 수 있습니다.");
            return mav;
        }

        CommentDto commentDto = commentService.getCommentById(commentNo);
        ModelAndView mav = new ModelAndView("board/detail?no=" + commentNo);
        mav.addObject("comment", commentDto);
        return mav;
    }


    @ApiOperation(value = "댓글 수정 화면", notes = "댓글을 수정하기 위한 화면을 보여줍니다")
    @GetMapping("/starroad/comment/update")
    public ModelAndView updateComment(@ApiParam(value = "수정할 댓글 번호", example = "1") @RequestParam("no") int commentNo,
                                      @ApiIgnore HttpSession session) {

        if (session.getAttribute("currentUser") == null) {
            ModelAndView mav = new ModelAndView("redirect:/starroad/login");
            mav.addObject("message", "로그인 후에 댓글을 작성할 수 있습니다.");
            return mav;
        }

        ModelAndView mav = new ModelAndView("comment/update");

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
    @ApiOperation(value = "댓글 수정 처리", notes = "댓글을 수정 내용을 처리하고 DB에 업데이트합니다")
    @PostMapping("/starroad/comment/doUpdate")
    public ModelAndView processUpdateComment(
            @ApiParam(value = "수정할 댓글 번호", example = "1") @RequestParam("no") int commentNo,
            @ApiParam(value = "수정할 댓글 내용") @RequestParam("content") String content,
            @ApiParam(value = "게시글 번호", example = "1") @RequestParam("board") int boardNo,
            @ApiIgnore HttpSession session) {

        if (session.getAttribute("currentUser") == null) {
            ModelAndView mav = new ModelAndView("redirect:/starroad/login");
            mav.addObject("message", "로그인 후에 댓글을 작성할 수 있습니다.");
            return mav;
        }

        CommentDto commentDto = new CommentDto();
        commentDto.setNo(commentNo);
        commentDto.setContent(content);

        commentService.updateComment(commentDto);

        ModelAndView mav = new ModelAndView("redirect:/starroad/board/detail?no=" + boardNo);
        return mav;
    }

    @ApiOperation(value = "댓글 삭제", notes = "댓글을 삭제 할 수 있다")
    @PostMapping("/starroad/comment/delete")
    public ModelAndView deleteComment(
            @ApiParam(value = "삭제할 댓글 번호", example = "1")
            @RequestParam("no") int commentNo,
            @ApiIgnore HttpSession session) {

        if (session.getAttribute("currentUser") == null) {
            ModelAndView mav = new ModelAndView("redirect:/starroad/login");
            mav.addObject("message", "로그인 후에 댓글을 작성할 수 있습니다.");
            return mav;
        }

        int commentDelete = commentService.findByNo(commentNo).get().getBoard().getNo();
        commentService.deleteComment(commentNo);

        ModelAndView mav = new ModelAndView("redirect:/starroad/board/detail?no=" + commentDelete);
        return mav;
    }

}

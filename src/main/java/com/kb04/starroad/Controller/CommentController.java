package com.kb04.starroad.Controller;

import com.kb04.starroad.Dto.MemberDto;
import com.kb04.starroad.Dto.board.BoardResponseDto;
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

        if (content == null || content.trim().isEmpty()) {
            ModelAndView mav = new ModelAndView("redirect:/starroad/board/detail?no=" + boardNo);
            mav.addObject("message", "댓글 내용을 입력해주세요.");
            return mav;
        }

        int number = commentService.createComment(content, boardNo, ((MemberDto)session.getAttribute("currentUser")).getNo());

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
                                      @ApiParam(value = "게시글 번호") @RequestParam("boardNo") int boardNo,
                                      @ApiIgnore HttpSession session) {


        if (session.getAttribute("currentUser") == null) {
            ModelAndView mav = new ModelAndView("redirect:/starroad/login");
            mav.addObject("message", "로그인 후에 댓글을 작성할 수 있습니다.");
            return mav;
        }

        MemberDto currentUser = (MemberDto) session.getAttribute("currentUser");
        String currentUserId = currentUser.getId();

        ModelAndView mav = new ModelAndView("comment/update");

        // 로그인한 사용자와 댓글 작성자가 동일한지 확인
        if (commentService.canUpdate(commentNo, currentUserId)) {
            CommentDto commentDto = commentService.getUpdateComment(commentNo);
            mav.addObject("comment", commentDto);
            mav.setViewName("comment/update");
        }
        else{
            mav.setViewName("redirect:/starroad/board/detail?no=" + boardNo);
            mav.addObject("message", "다른 사용자의 댓글을 수정할 수 없습니다.");
        }


        Optional<Comment> commentOptional = commentService.findByNo(commentNo);

        if(commentOptional.isPresent()) {
            Comment comment = commentOptional.get();
            CommentDto commentDto = comment.toCommentDto();
            mav.addObject("comment", commentDto);
            BoardResponseDto dto = boardService.detailBoard(boardNo);
            if(dto.getComments().equals(null)) mav.addObject("noComments", true);
            mav.addObject("board", dto);
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


        ModelAndView mav = new ModelAndView();
        if (session.getAttribute("currentUser") == null) {
            mav = new ModelAndView("redirect:/starroad/login");
            mav.addObject("message", "로그인 후에 댓글을 작성할 수 있습니다.");
            return mav;
        }

        MemberDto currentUser = (MemberDto) session.getAttribute("currentUser");
        String currentUserId = currentUser.getId();
        if (commentService.canDelete(commentNo, currentUserId)) {
            // 현재 사용자가 게시글 삭제 가능한 경우
            int boardNo = commentService.deleteComment(commentNo);
            mav.setViewName("redirect:/starroad/board/detail?no=" + boardNo);

        } else {
            // 현재 사용자가 게시글 삭제 불가
            // 능한 경우 or 게시글 존재하지 않는 경우
            mav.setViewName("board/deleteError");
        }
        return mav;
    }

}

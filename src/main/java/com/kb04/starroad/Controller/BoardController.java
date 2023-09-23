package com.kb04.starroad.Controller;

import com.kb04.starroad.Dto.MemberDto;
import com.kb04.starroad.Dto.board.BoardResponseDto;
import com.kb04.starroad.Dto.board.CommentDto;
import com.kb04.starroad.Entity.Board;
import com.kb04.starroad.Service.BoardService;
import com.kb04.starroad.Service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Api(tags = "게시판 API")
@RestController
public class BoardController {


    private final BoardService boardService;

    private final CommentService commentService;

    public BoardController(BoardService boardService, CommentService commentService) {
        this.boardService = boardService;
        this.commentService = commentService;
    }


    @ApiOperation(value = "게시판 메인", notes = "게시판 메인화면을 보여줍니다")
    @GetMapping("/starroad/board/main")
    public ModelAndView boardList() {
        ModelAndView mav = new ModelAndView("board/main");

        PageRequest freepageRequest = PageRequest.of(0, 10, Sort.by("regdate").descending());
        PageRequest authpageRequest = PageRequest.of(0, 10, Sort.by("regdate").descending());
        PageRequest popularpageRequest = PageRequest.of(0, 10, Sort.by("regdate").descending());

        mav.addObject("freeBoard", boardService.boardListFree(freepageRequest));
        mav.addObject("authBoard", boardService.boardListAuth(authpageRequest));
        mav.addObject("popularBoard", boardService.boardListPopular(popularpageRequest));

        return mav;
    }

    @ApiOperation(value = "게시물 글쓰기 폼", notes = "게시물 글쓰기 폼으로 이동합니다")
    @GetMapping("/starroad/board/write")
    public ModelAndView board(@ApiIgnore HttpSession session, RedirectAttributes redirectAttributes) {
        ModelAndView mav = new ModelAndView();
        if (session.getAttribute("currentUser") == null) {
            redirectAttributes.addFlashAttribute("error", "게시물 쓰기는 로그인이 필요한 서비스입니다");
            mav.setViewName("redirect:/starroad/login");
            return mav;
        }
        mav = new ModelAndView("board/write");
        return mav;
    }

    @ApiOperation(value = "자유,인증 게시판", notes = "자유,인증 게시판으로 갈 수 있습니다.")
    @GetMapping("/starroad/board/free")
    public ModelAndView boardList(
            @ApiParam(value = "게시글타입") @RequestParam(name = "type", defaultValue = "F") String type,
            @ApiIgnore HttpServletRequest request) {

        ModelAndView mav = new ModelAndView("board/board");

        List<BoardResponseDto> boardList = null;

        if ("F".equals(type)) { // 자유게시판
            boardList = boardService.selectBoardAllOrderByDate("F");

        } else if ("C".equals(type)) { // 인증방
            boardList = boardService.selectBoardAllOrderByDate("C");
        }
        else {
            throw new IllegalArgumentException("잘못된 type 값입니다.");
        }

        mav.addObject("freeBoardPage", boardList);
        mav.addObject("type", type); // View에서 현재 type 값을 사용할 수 있도록 추가

        return mav;
    }

    @ApiOperation(value = "인기게시판", notes = "인기게시판을 보여줍니다")
    @GetMapping("/starroad/board/popular")
    public ModelAndView popularBoardList(
            @ApiIgnore HttpServletRequest request) {

        ModelAndView mav = new ModelAndView("board/board");

        List<BoardResponseDto> boardList = boardService.selectPopularBoard();

        mav.addObject("popularBoardPage", boardList);
        mav.addObject("type", "popular");

        return mav;
    }

    @ApiOperation(value = "게시글 수정 폼", notes = "게시글을 폼을 볼 수 있습니다")
    @GetMapping("/starroad/board/update")
    public ModelAndView updateBoard(
            @ApiParam(value = "게시글 번호") @RequestParam("no") int no,
            @ApiIgnore HttpSession session, RedirectAttributes redirectAttributes) {

        ModelAndView mav = new ModelAndView();

        MemberDto currentUser = (MemberDto) session.getAttribute("currentUser"); // 세션에서 현재 로그인한 사용자의 ID
        if (currentUser == null){
            redirectAttributes.addFlashAttribute("error", "게시글 수정은 로그인이 필요한 서비스입니다");
            mav.setViewName("redirect:/starroad/login");
        }

        mav = new ModelAndView("board/update");
        String currentUserId = currentUser.getId();
        if (boardService.canUpdate(no, currentUserId)) {
            BoardResponseDto boardResponseDto = boardService.getUpdateBoard(no);
            mav.addObject("board", boardResponseDto);
        }
        else{
            mav.setViewName("redirect:/starroad/board/detail?no=" + no);
        }
        return mav;
    }

    @ApiOperation(value = "게시글 수정 기능", notes = "게시글을 수정할 수 있습니다")
    @PostMapping("/starroad/board/updatepro")
    public ModelAndView updateBoardPro(
            @ApiParam(value = "게시글 번호")@RequestParam("no") int no,
            @ApiParam(value = "게시글 제목") @RequestParam("title") String title,
            @ApiParam(value = "게시글 내용") @RequestParam("content") String content,
            @ApiParam(value = "이미지") @RequestParam(value = "newImage") MultipartFile newImage) {

        ModelAndView errorModelAndView = new ModelAndView("error");
        ModelAndView modelAndView = new ModelAndView("redirect:/starroad/board/detail?no=" + no);

        try {
            if (boardService.updateBoard(no, title, content, newImage)){
                return modelAndView;
            } else {
                return errorModelAndView;
            }
        } catch (IOException e) {
            errorModelAndView.addObject("errorMessage", e.getMessage()); // TODO : 에러 페이지 - 게시물 수정 에러
            return errorModelAndView;
        }
    }

    @ApiOperation(value = "게시글 작성", notes = "게시글을 작성할 수 있습니다")
    @PostMapping("/starroad/board/writepro")
    public ModelAndView boardWritePro(
            @ApiIgnore HttpSession session, RedirectAttributes redirectAttributes,
            @ApiParam(value = "게시글 종류") @RequestParam("type") String type,
            @ApiParam(value = "게시글 상세 종류") @RequestParam("detailType") String detailType,
            @ApiParam(value = "게시글 제목") @RequestParam("title") String title,
            @ApiParam(value = "게시글 내용") @RequestParam("content") String content,
            @ApiParam(value = "게시글 이미지") @RequestParam("image") MultipartFile imageFile
    ) {

        ModelAndView mav = new ModelAndView();

        if (session.getAttribute("currentUser") == null) {
            redirectAttributes.addFlashAttribute("error", "게시물 작성은 로그인이 필요한 서비스입니다");
            mav.setViewName("redirect:/starroad/login");
            return mav;
        }

        try {
            MemberDto dto = (MemberDto) session.getAttribute("currentUser");
            boardService.writeBoard(dto.getId(), type, detailType, title, content, imageFile);

            String url = "redirect:/starroad/board/free?type=" + type;
            return new ModelAndView(url);

        } catch (IOException e) {
            e.printStackTrace();
            mav = new ModelAndView("/error");
            mav.addObject("message", "이미지 업로드에 실패했습니다."); // TODO : 에러 페이지 - 이미지 업로드 에러
            return mav;
        }
    }

    @ApiOperation(value = "게시글 삭제", notes = "게시글을 삭제할 수 있습니다")
    @GetMapping("/starroad/board/delete")
    public ModelAndView deleteBoard(
            @ApiParam(value = "게시글 번호") @RequestParam Integer no, @ApiIgnore HttpSession session, RedirectAttributes redirectAttributes) {

        ModelAndView mav = new ModelAndView();

        if (session.getAttribute("currentUser") == null) {
            redirectAttributes.addFlashAttribute("error", "게시물 삭제는 로그인이 필요한 서비스입니다");
            mav.setViewName("redirect:/starroad/login");
            return mav;
        }

        MemberDto currentUser = (MemberDto) session.getAttribute("currentUser");
        String currentUserId = currentUser.getId();

        if (boardService.canDelete(no, currentUserId)) {
            boardService.deleteBoard(no);
            mav.setViewName("redirect:/starroad/board/main");
        } else{
            mav.setViewName("board/deleteError");  // TODO : 에러 페이지 - 삭제 에러
        }
        return mav;
    }

    @ApiOperation(value = "게시글 상세", notes = "게시글을 상세를 볼 수 있습니다")
    @GetMapping("/starroad/board/detail")
    public ModelAndView getBoardDetail(@ApiParam(value = "게시글 번호") @RequestParam("no") int no) {

        ModelAndView mav = new ModelAndView();
        mav = new ModelAndView("board/detail");

        BoardResponseDto dto = boardService.detailBoard(no);
        //if(dto.equals(null)) mav.addObject("error", "게시글을 찾을 수 없습니다.");
        if(dto.getComments().equals(null)) mav.addObject("noComments", true);
        mav.addObject("board", dto);

        return mav;
    }

    @ApiOperation(value = "게시글 추천", notes = "게시글을 추천할 수 있습니다")
    @PostMapping("/starroad/board/like")
    public ModelAndView handleLike(
            @ApiParam(value = "게시글 번호") @RequestParam("board") int boardNo,
            @ApiIgnore HttpSession session, RedirectAttributes redirectAttributes) {

        ModelAndView mav = new ModelAndView();
        MemberDto memberDto = (MemberDto) session.getAttribute("currentUser");

        if (memberDto == null ){
            redirectAttributes.addFlashAttribute("error", "게시물 추천은 로그인이 필요한 서비스입니다");
            mav.setViewName("redirect:/starroad/login");
            return mav;
        } else if (boardService.hasLiked(boardNo, memberDto.getId())) {
            ModelAndView lMav= new ModelAndView("redirect:/starroad/board/detail?no=" + boardNo);
            lMav.addObject("message", "로그인 후에 좋아요를 누르거나 이미 좋아요한 게시글입니다.");  //TODO : 메시지가 표시되는 곳이 있나요?
            return lMav;
        }
        else {
            boardService.increaseLikes(boardNo, memberDto.getId());
            return new ModelAndView("redirect:/starroad/board/detail?no=" + boardNo);
        }
    }
}

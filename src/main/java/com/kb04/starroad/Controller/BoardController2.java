package com.kb04.starroad.Controller;

import com.kb04.starroad.Entity.Board;
import com.kb04.starroad.Service.BoardService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BoardController2 {

    @Autowired
    private BoardService2 boardService;
    @GetMapping("/starroad/boardWrite")
    public ModelAndView board() {
        ModelAndView mav = new ModelAndView("board/boardWrite");
        return mav;
    }
    @GetMapping("/starroad/boardMain")
    public ModelAndView boardMain() {
        ModelAndView mav = new ModelAndView("board/boardMain");
        return mav;
    }

    //자유게시판 요청
    @GetMapping("/starroad/freeboard")
    public ModelAndView boardList(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "6") int size,
            @RequestParam(name = "type", defaultValue = "0") String type,
            HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("board/freeBoard");

        // 페이징 정보 설정
        PageRequest pageable = PageRequest.of(page, size, Sort.by("regdate").descending());



        Page<Board> boardPage;

        if ("0".equals(type)) {
            // type이 "0"인 경우 자유게시판 목록 조회
            boardPage = boardService.findPaginated(pageable);
        } else if ("1".equals(type)) {
            // type이 "1"인 경우 인증방 목록 조회
            boardPage = boardService.findAuthenticatedPaginated(pageable);
        } else {
            // 잘못된 type 값이 들어온 경우 예외 처리
            throw new IllegalArgumentException("잘못된 type 값입니다.");
        }

        mav.addObject("freeBoardPage", boardPage);
        mav.addObject("type", type); // View에서 현재 type 값을 사용할 수 있도록 추가

        return mav;
    }



    @PostMapping("/starroad/writepro")
    public ResponseEntity<String> boardWritePro(
            @RequestParam("type") String type,
            @RequestParam("detailType") String detailType,
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("image") MultipartFile imageFile
    ) {
        try {
            boardService.writeBoard(type, detailType, title, content, imageFile);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Location", "/starroad/boardMain");
            return new ResponseEntity<>("", headers, HttpStatus.FOUND);
        } catch (IOException e) {
            e.printStackTrace();
            // 이미지 업로드 실패 시 에러 응답
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Image upload failed.");
        }
    }
}

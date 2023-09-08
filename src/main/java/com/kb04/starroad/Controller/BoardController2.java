package com.kb04.starroad.Controller;

import com.kb04.starroad.Dto.board.BoardDto;
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

import java.io.IOException;

@RestController
public class BoardController2 {

    @Autowired
    private BoardService2 boardService;
    @GetMapping("/starroad/boardWrite")
    public ModelAndView board() {
        ModelAndView mav = new ModelAndView("board/boardWrite");
        return mav;
    }


    @GetMapping("/starroad/freeboard")
    public ModelAndView boardList(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "6") int size) {

        ModelAndView mav = new ModelAndView("board/freeBoard");

        // 페이징 정보 설정
        PageRequest pageable = PageRequest.of(page , size, Sort.by("regdate").descending());

        // 게시글 목록 조회
        Page<Board> boardPage = boardService.findPaginated(pageable);

        mav.addObject("freeBoardPage", boardPage);

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
            headers.add("Location", "/starroad/freeboard");
            return new ResponseEntity<>("", headers, HttpStatus.FOUND);
        } catch (IOException e) {
            e.printStackTrace();
            // 이미지 업로드 실패 시 에러 응답
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Image upload failed.");
        }
    }
}

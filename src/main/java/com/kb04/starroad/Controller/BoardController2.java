package com.kb04.starroad.Controller;

import com.kb04.starroad.Dto.BoardDto;
import com.kb04.starroad.Repository.BoardRepository;
import com.kb04.starroad.Service.BoardService2;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.List;

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
    public ModelAndView freeBoard(){
        ModelAndView mav =new ModelAndView("board/freeBoard");
        return mav;
    }
    @GetMapping("/starroad/boardList")
    public List<BoardDto>  boardList(){
        List<BoardDto> boardList = boardService.boardList();
        return boardList;
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
            // 이미지 파일이 업로드된 경우에만 처리
            if (!imageFile.isEmpty()) {
                // 이미지 파일을 byte 배열로 변환
                byte[] imageBytes = imageFile.getBytes();

                // BoardDto 객체 생성 및 데이터 설정
                BoardDto board = new BoardDto();
                board.setType(type);
                board.setDetailType(detailType);
                board.setTitle(title);
                board.setContent(content);
                board.setImage(imageBytes);

                // 게시글 저장
                boardService.write(board);

                HttpHeaders headers = new HttpHeaders();
                headers.add("Location", "/starroad/freeboard");
                return new ResponseEntity<>("", headers, HttpStatus.FOUND);
            } else {
                // 이미지 파일이 업로드되지 않은 경우 에러 응답
                return ResponseEntity.badRequest().body("Image file is empty.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            // 이미지 업로드 실패 시 에러 응답
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Image upload failed.");
        }
    }
}

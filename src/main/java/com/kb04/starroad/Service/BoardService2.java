package com.kb04.starroad.Service;

import com.kb04.starroad.Dto.BoardDto;
import com.kb04.starroad.Repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Service
public class BoardService2 {


    @Autowired
    private BoardRepository boardRepository;

    public void write(BoardDto board) {  //entity를 매개변수로 받음

        boardRepository.save(board);        //새로운 게시물이 데이터베이스에 추가됩니다.
    }


    //이미지 업로드 로직
    public void writeBoard(String type, String detailType, String title, String content, MultipartFile imageFile) throws IOException {
        if (imageFile != null && !imageFile.isEmpty()) {
            byte[] imageBytes = imageFile.getBytes();

            BoardDto board = new BoardDto();
            board.setType(type);
            board.setDetailType(detailType);
            board.setTitle(title);
            board.setContent(content);
            board.setImage(imageBytes);

            boardRepository.save(board);
        } else {
            // 이미지 파일이 없는 경우에도 게시글 저장
            BoardDto board = new BoardDto();
            board.setType(type);
            board.setDetailType(detailType);
            board.setTitle(title);
            board.setContent(content);

            boardRepository.save(board);
        }
    }
    public Page<BoardDto> findPaginated(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    public List<BoardDto> boardList(){
        List<BoardDto> boardList = boardRepository.findAll();
        return boardList;
    }
}

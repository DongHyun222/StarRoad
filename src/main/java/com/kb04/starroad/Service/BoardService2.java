package com.kb04.starroad.Service;

import com.kb04.starroad.Dto.BoardDto;
import com.kb04.starroad.Repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<BoardDto> boardList(){
        List<BoardDto> boardList = boardRepository.findAll();
        return boardList;
    }
}

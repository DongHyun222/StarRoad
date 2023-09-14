package com.kb04.starroad.Service;

import com.kb04.starroad.Dto.board.BoardRequestDto;
import com.kb04.starroad.Dto.board.BoardResponseDto;
import com.kb04.starroad.Dto.board.CommentDto;
import com.kb04.starroad.Entity.Board;
import com.kb04.starroad.Repository.BoardRepository;
import com.kb04.starroad.Repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;


import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService2 {


    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private CommentRepository commentRepository;
    public void write(Board board) {  //entity를 매개변수로 받음

        boardRepository.save(board);  //새로운 게시물이 데이터베이스에 추가됩니다.
    }


    //이미지 업로드 로직
    public void writeBoard(String type, String detailType, String title, String content, MultipartFile imageFile) throws IOException {
        BoardRequestDto boardDto = new BoardRequestDto();
        boardDto.setType(type);
        boardDto.setDetailType(detailType);
        boardDto.setTitle(title);
        boardDto.setContent(content);

        if (imageFile != null && !imageFile.isEmpty()) {
            byte[] imageBytes = imageFile.getBytes();
            boardDto.setImage(imageBytes);
        }

        // DTO를 Entity로 변환
        Board board = boardDto.toEntity();

        // boardRepository를 통해 Entity를 저장
        boardRepository.save(board);
        }

    public Page<Board> findPaginated(Pageable pageable) {
        return boardRepository.findByType("F", pageable); // "0"은 자유게시판 타입에 해당하는 것으로 가정합니다.
    }


    public Page<Board> findAuthenticatedPaginated(Pageable pageable) {
        return boardRepository.findByType("C", pageable); // "1"은 인증방 타입에 해당하는 것으로 가정합니다.
    }

    public List<Board> boardList(){
        List<Board> boardList = boardRepository.findAll();
        return boardList;
    }
    public Page<Board> getPopularBoards(Pageable pageable) {
        return boardRepository.findAllByOrderByLikesDesc(pageable);
    }

    public Optional<Board> findById(Integer no) {
        return boardRepository.findById(no);
    }

    public void deleteBoard(Integer no) {
        boardRepository.deleteById(no);
    }

}

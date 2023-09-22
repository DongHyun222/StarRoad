package com.kb04.starroad.Service;

import com.kb04.starroad.Dto.board.BoardRequestDto;
import com.kb04.starroad.Dto.board.BoardResponseDto;
import com.kb04.starroad.Entity.Board;
import com.kb04.starroad.Entity.Heart;
import com.kb04.starroad.Entity.Member;
import com.kb04.starroad.Repository.BoardRepository;
import com.kb04.starroad.Repository.HeartRepository;
import com.kb04.starroad.Repository.MemberRepository;
import com.kb04.starroad.Repository.Specification.BoardSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.*;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final HeartRepository heartRepository;

    /**
     * 게시판 메인 용 - 자유 게시판
     */
    public Page<Board> boardListFree(Pageable pageable) {
        Page<Board> boardList;

        boardList = boardRepository.findAllByTypeAndStatusOrderByRegdateDesc("F", 'Y', pageable);

        return boardList;
    }

    /**
     * 게시판 메인 용 - 인증 게시판
     */
    public Page<Board> boardListAuth(Pageable pageable) {
        Page<Board> boardList;

        boardList = boardRepository.findAllByTypeAndStatusOrderByRegdateDesc("C", 'Y', pageable);

        return boardList;
    }

    /**
     * 게시판 메인 용 - 인기 게시판
     */
    public Page<Board> boardListPopular(Pageable pageable) {
        Page<Board> boardList;

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -7);
        Date oneWeekAgo = calendar.getTime();
        boardList = boardRepository.findAllByStatusAndLikesGreaterThanEqualAndRegdateAfterOrderByLikesDesc('Y', 10, oneWeekAgo, pageable);

        return boardList;
    }

    /**
     * 게시판 모든 글 출력 - 자유 게시판, 인증방
     */
    public List<BoardResponseDto> selectBoardAllOrderByDate(String type) {
        List<Board> boardList = boardRepository.findAll(BoardSpecification.searchBoardByStatusAndType(type, 'Y'));

        List<BoardResponseDto> dtoList = new ArrayList<>();
        for(Board board : boardList){
            dtoList.add(board.toBoardResponseDto());
        }

        return dtoList;
    }

    /**
     * 게시판 모든 글 출력 - 인기글
     */
    public List<BoardResponseDto> selectPopularBoard() {
        List<Board> boardList = boardRepository.findAllByStatusOrderByLikesDesc('Y');
        List<BoardResponseDto> dtoList = new ArrayList<>();

        for(Board board : boardList) {
            dtoList.add(board.toBoardResponseDto());
        }
        return dtoList;
    }

    /**
     * 수정하려는 게시물이 현재 로그인한 유저가 작성한 게시물인지 검사
     * @param no 게시물 번호
     * @param currentUserId 현재 로그인한 유저
     */
    public boolean canUpdate(int no, String currentUserId) {
        Optional<Board> boardOptional = findById(no);

        if (boardOptional.isPresent()) {
            Board board = boardOptional.get();
            Member writer = board.getMember();

            if (writer != null) {
                String writerId = writer.getId();
                return currentUserId != null && currentUserId.equals(writerId);
            }
        }
        return false;
    }

    /**
     * canUpdate가 true일 경우 수정하려는 게시물 내용 return
     * @param no 게시물 번호
     */
    public BoardResponseDto getUpdateBoard(int no) {
        return boardRepository.findByNo(no).toBoardResponseDto();
    }

    /**
     * 게시글 수정
     * @param no 게시글 번호
     * @param title 게시글 타이틀
     * @param content 게시글 내용
     * @param newImage 게시글 이미지
     */
    @Transactional
    public boolean updateBoard(int no, String title, String content, MultipartFile newImage) throws IOException {

        Optional<Board> optionalBoard = boardRepository.findById(no);

        if(optionalBoard.isPresent()){
            Board board2 = optionalBoard.get();
            board2.update(title, content, newImage.isEmpty() ? board2.getImage() : newImage.getBytes());
            boardRepository.save(board2);
            return true;
        }
        return false;
    }











    public BoardResponseDto detailBoard(){
        return null;
    }
    public void write(Board board) {  //entity를 매개변수로 받음

        boardRepository.save(board);  //새로운 게시물이 데이터베이스에 추가됩니다.
    }


    //이미지 업로드 로직
    public void writeBoard(String memberId, String type, String detailType,
                           String title, String content,
                           MultipartFile imageFile) throws IOException {
        // Member 객체 찾기
        Optional<Member> optionalMember = memberRepository.findById(memberId);

        Member member = optionalMember.orElseThrow(() -> new IllegalArgumentException("Invalid member ID: " + memberId));

        BoardRequestDto boardDto = new BoardRequestDto();
        boardDto.setType(type);
        boardDto.setDetailType(detailType);
        boardDto.setTitle(title);
        boardDto.setContent(content);

        if (imageFile != null && !imageFile.isEmpty()) {
            byte[] imageBytes = imageFile.getBytes();
            boardDto.setImage(imageBytes);
        }

        // DTO to Entity 변환
        Board board = boardDto.toEntity();

        // Member 객체와 연결
        board.setMember(member);

        // Entity 저장
        boardRepository.save(board);
    }










    public Optional<Board> findById(int no) {

        return boardRepository.findById(no);
    }



    public void deleteBoard(Integer no) {
        Optional<Board> boardOptional = boardRepository.findById(no);

        if (boardOptional.isPresent()) {
            // 게시글이 존재하는 경우에만 삭제 수행
            boardRepository.deleteById(no);
        } else {
            throw new NoSuchElementException("게시글이 존재하지 않습니다.");
        }
    }

    public boolean canDelete(Integer no, String currentUserId) {
        Optional<Board> boardOptional = boardRepository.findById(no);

        if (boardOptional.isPresent()) {
            Board board = boardOptional.get();
            Member writer = board.getMember();

            if (writer != null) {
                String writerId = writer.getId();
                return writerId.equals(currentUserId);
            }
        }
        return false;
    }




    public void increaseLikes(int boardNo, String memberId) {
        Optional<Board> optionalBoard = boardRepository.findById(boardNo);
        if (optionalBoard.isPresent()) {
            Board board = optionalBoard.get();
            int currentLikesCount = board.getLikes();

            board.setLikes(currentLikesCount + 1);
            // Save a new like entity as well to keep track of who liked the post.
            Member member = memberRepository.findById(memberId).get();
            heartRepository.save(Heart.builder()
                    .member(member)
                    .board(board)
                    .build());
        }
    }

    public boolean hasLiked(int boardNo, String memberId) {
        Member member = memberRepository.findById(memberId).get();
        Optional<Heart> likes = heartRepository.findByMemberNoAndBoardNo(member.getNo(), boardNo);

        return !likes.isPresent();
    }
}

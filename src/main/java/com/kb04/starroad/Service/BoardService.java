package com.kb04.starroad.Service;

import com.kb04.starroad.Dto.board.BoardRequestDto;
import com.kb04.starroad.Dto.board.BoardResponseDto;
import com.kb04.starroad.Dto.board.CommentDto;
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
import java.time.LocalDateTime;
import java.util.*;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final HeartRepository heartRepository;
    private final CommentService commentService;

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
//    public List<BoardResponseDto> selectPopularBoard() {
//        List<Board> boardList = boardRepository.findAllByStatusOrderByLikesDesc('Y');
//        List<BoardResponseDto> dtoList = new ArrayList<>();
//
//        for(Board board : boardList) {
//            dtoList.add(board.toBoardResponseDto());
//        }
//        return dtoList;
//    }


    // 게시판 모든 글 출력 - 인기글
    public List<BoardResponseDto> selectPopularBoard() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -7);
        Date oneWeekAgo = calendar.getTime();

        List<Board> boardList = boardRepository.findAllByStatusAndLikesGreaterThanEqualAndRegdateAfterOrderByLikesDesc('Y', 10, oneWeekAgo);

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
        Optional<Board> boardOptional = boardRepository.findById(no);

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
     * 게시물 수정
     * @param no 게시물 번호
     * @param title 게시물 타이틀
     * @param content 게시물 내용
     * @param newImage 게시물 이미지
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


    /**
     * 게시물 등록
     * @param memberId 로그인한 회원 아이디
     * @param type 게시물 type
     * @param detailType 게시물 detail type
     * @param title 게시물 제목
     * @param content 게시물 내용
     * @param imageFile 게시물 이미지 파일
     */
    public void writeBoard(String memberId, String type, String detailType,
                           String title, String content, MultipartFile imageFile) throws IOException {

        Optional<Member> optionalMember = memberRepository.findById(memberId);
        Member member = optionalMember.get();

        BoardRequestDto boardDto = new BoardRequestDto();
        boardDto.setType(type);
        boardDto.setDetailType(detailType);
        boardDto.setTitle(title);
        boardDto.setContent(content);
        boardDto.setMember(member);
        boardDto.setImage(imageFile.isEmpty() ? null : imageFile.getBytes());

        Board board = boardDto.toEntity();
        boardRepository.save(board);
    }

    /**
     * 삭제하려는 게시물이 현재 로그인한 유저가 작성한 게시물인지 검사
     * @param no 게시글 번호
     * @param currentUserId 로그인한 회원 아이디
     */
    public boolean canDelete(int no, String currentUserId) {

        Board board = boardRepository.findByNo(no);
        Member writer = board.getMember();

        return writer.getId().equals(currentUserId);
    }

    /**
     * 게시물 삭제
     * @param no 게시물 번호
     */
    public void deleteBoard(int no) {
        boardRepository.deleteById(no);
    }

    /**
     * 게시물 상세 보기
     * @param no 게시물 번호
     */
    public BoardResponseDto detailBoard(int no){

        Board board = boardRepository.findByNo(no);
        if (board == null) return null;

        BoardResponseDto resultDto = board.toBoardResponseDto();
        List<CommentDto> comments = commentService.findByBoard(board);
        resultDto.setComments(comments);
        return resultDto;
    }

    /**
     * 로그인 한 유저가 게시물 좋아요 눌렀는지 안 했는지 검사
     * @param boardNo 게시물 번호
     * @param memberId 로그인한 유저 아이디
     * @return 이미 좋아요 눌렀다면 true, 아니라면 false 리턴
     */
    public boolean hasLiked(int boardNo, String memberId) {
        Member member = memberRepository.findById(memberId).get();
        Optional<Heart> likes = heartRepository.findByMemberNoAndBoardNo(member.getNo(), boardNo);

        return likes.isPresent();
    }

    /**
     * 게시물 좋아요 누르기
     * @param boardNo 게시물 번호
     * @param memberId 로그인한 유저 아이디
     */
    public void increaseLikes(int boardNo, String memberId) {

        Board board = boardRepository.findByNo(boardNo);
        int currentLikesCount = board.getLikes();
        board.setLikes(currentLikesCount + 1);

        Member member = memberRepository.findById(memberId).get();
        heartRepository.save(Heart.builder()
                .member(member)
                .board(board)
                .build());
        }
}

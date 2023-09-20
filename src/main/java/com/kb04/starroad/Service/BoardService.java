package com.kb04.starroad.Service;

import com.kb04.starroad.Dto.board.BoardRequestDto;
import com.kb04.starroad.Entity.Board;
import com.kb04.starroad.Entity.Heart;
import com.kb04.starroad.Entity.Member;
import com.kb04.starroad.Repository.BoardRepository;
import com.kb04.starroad.Repository.HeartRepository;
import com.kb04.starroad.Repository.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final HeartRepository heartRepository;

    public BoardService(BoardRepository boardRepository, MemberRepository memberRepository, HeartRepository heartRepository) {
        this.boardRepository = boardRepository;
        this.memberRepository = memberRepository;
        this.heartRepository = heartRepository;
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

    public Page<Board> findPaginated(Pageable pageable) {
        return boardRepository.findByTypeAndStatus("F", 'Y', pageable); // "0"은 자유게시판 타입에 해당하는 것으로 가정합니다.
    }


    public Page<Board> findAuthenticatedPaginated(Pageable pageable) {
        return boardRepository.findByTypeAndStatus("C", 'Y', pageable); // "1"은 인증방 타입에 해당하는 것으로 가정합니다.
    }


    //0914 여기 수정중
    public Page<Board> boardListFree(Pageable pageable) {
        Page<Board> boardList;

        boardList = boardRepository.findAllByTypeAndStatusOrderByRegdateDesc("F", 'Y', pageable);

        return boardList;
    }

    public Page<Board> boardListAuth(Pageable pageable) {
        Page<Board> boardList;

        boardList = boardRepository.findAllByTypeAndStatusOrderByRegdateDesc("C", 'Y', pageable);

        return boardList;
    }

    public Page<Board> boardListpopular(Pageable pageable) {
        Page<Board> boardList;

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -7);
        Date oneWeekAgo = calendar.getTime();
        boardList = boardRepository.findAllByStatusAndLikesGreaterThanEqualAndRegdateAfterOrderByLikesDesc('Y', 10, oneWeekAgo, pageable);

        return boardList;
    }


    public Page<Board> getPopularBoards(Pageable pageable) {
        return boardRepository.findAllByStatusOrderByLikesDesc('Y', pageable);
    }

    public Optional<Board> findById(Integer no) {

        return boardRepository.findById(no);
    }

    @Transactional
    public void updateBoard(BoardRequestDto boardRequestDto) {
        // 게시물 번호를 이용하여 해당 게시물을 조회합니다.

        Optional<Board> optionalBoard = boardRepository.findById(boardRequestDto.getNo());

        Board board2 = optionalBoard.get();
        board2.update(boardRequestDto.getTitle(), boardRequestDto.getContent());

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

    public boolean canUpdate(Integer no, String currentUserId) {
        Optional<Board> boardOptional = findById(no);

        if (boardOptional.isPresent()) {
            Board board = boardOptional.get();
            Member writer = board.getMember();  // 게시글의 작성자 가져오기


            if (writer != null) {  // 작성자 정보가 있는 경우
                String writerId = writer.getId();
                return currentUserId != null && currentUserId.equals(writerId);
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

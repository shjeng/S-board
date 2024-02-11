package imsh.project.web.board.controller;

import imsh.project.domain.board.dto.request.board.PatchBoardRequestDto;
import imsh.project.domain.board.dto.request.board.PostBoardRequestDto;
import imsh.project.domain.board.dto.request.board.PostCommentRequestDto;
import imsh.project.domain.board.dto.response.board.*;
import imsh.project.domain.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/board")
@RequiredArgsConstructor
@Slf4j
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/{boardId}") // 게시물 상세보기
    public ResponseEntity<? super GetBoardResponseDto> getBoard(@PathVariable("boardId") Long boardId){
        ResponseEntity<? super GetBoardResponseDto> response = boardService.getBoard(boardId);
        return response;
    }
    @GetMapping("/{boardId}/favorite-list") // 좋아요 리스트
    public ResponseEntity<? super GetFavoriteListResponseDto> getFavoriteList(
            @PathVariable("boardId") Long boardId){
        ResponseEntity<? super GetFavoriteListResponseDto> response = boardService.getFavoriteList(boardId);
        return response;
    }
    @GetMapping("/{boardId}/comment-list") // 댓글 리스트
    public ResponseEntity<? super GetCommentListResponseDto> getCommentList(
            @PathVariable("boardId") Long boardId){
        ResponseEntity<? super GetCommentListResponseDto> response = boardService.getCommentList(boardId);
        return response;
    }
    @GetMapping("/{boardId}/increase-view-count") // 조회수 증가
    public ResponseEntity<? super IncreaseViewCountResponseDto> increaseViewCount(@PathVariable("boardId")Long boardId){
        ResponseEntity<? super IncreaseViewCountResponseDto> response = boardService.increaseViewCount(boardId);
        return response;
    }

    @GetMapping("/latest-list") // 최신 게시물 가져오기
    public ResponseEntity<? super GetLatestBoardListResponseDto> getLatestBoardList(){
        ResponseEntity<? super GetLatestBoardListResponseDto> response = boardService.getLatestBoardList();
        return response;
    }
    @GetMapping("/top-3") // top 3
    public ResponseEntity<? super GetTop3BoardListResponseDto> getTop3BoardList(){
        ResponseEntity<? super  GetTop3BoardListResponseDto> response = boardService.getTop3BoardList();
        return response;
    }
    @GetMapping(value = {"/search-list/{searchWord}", "/search-list/{searchWord}/{preSearchWord}"}) // 검색어
    public ResponseEntity<? super GetSearchBoardListResponseDto> getSearchBoardList(
            @PathVariable ("searchWord") String searchWord,
            @PathVariable(value = "preSearchWord", required = false) String preSearchWord){
        ResponseEntity<? super GetSearchBoardListResponseDto> response = boardService.getSearchBoardList(searchWord, preSearchWord);
        return response;
    }

    @GetMapping("/user-board-list/{email}") // 특정 유저 게시물 리스트
    public ResponseEntity<? super GetUserBoardListResponseDto> getUserBoardList(@PathVariable("email") String email){
        ResponseEntity<? super GetUserBoardListResponseDto> response = boardService.getUserBoardList(email);
        return response;
    }
    @PostMapping("") // 게시물 작성 컨트롤러
    public ResponseEntity<? super PostBoardResponseDto> postBoard(
            @RequestBody @Valid PostBoardRequestDto requestBody,
            @AuthenticationPrincipal String email){
        ResponseEntity<? super PostBoardResponseDto> response = boardService.postBoard(requestBody,email);
        return response;
    }
    @PostMapping("/{boardId}/comment") // 댓글 작성 컨트롤러
    public ResponseEntity<? super PostCommentResponseDto> postComment(
            @RequestBody @Valid PostCommentRequestDto requestBody, @PathVariable("boardId") Long boardId,
            @AuthenticationPrincipal String email){
        ResponseEntity<? super PostCommentResponseDto> response = boardService.postCommnet(requestBody,boardId,email);
        return response;
    }

    @PatchMapping("/{boardId}") // 게시물 수정 컨트롤러
    public ResponseEntity<? super PatchBoardResponseDto> patchBoard(
            @RequestBody @Valid PatchBoardRequestDto requestBody, @PathVariable("boardId") Long id,
            @AuthenticationPrincipal String email){
        ResponseEntity<? super PatchBoardResponseDto> response = boardService.patchBoard(requestBody,id,email);
        return response;
    }

    @PutMapping("/{boardId}/favorite") // 좋아요 버튼 클릭 컨트롤러
    public ResponseEntity<? super PutFavoriteResponseDto> putFavorite(
            @PathVariable("boardId")Long boardId, @AuthenticationPrincipal String email){
        ResponseEntity<? super PutFavoriteResponseDto> response = boardService.putFavorite(boardId,email);
        return response;
    }
    @DeleteMapping("/{boardId}") // 게시물 삭제 컨트롤러
    public ResponseEntity<? super DeleteBoardResponseDto> deleteBoard(
            @PathVariable("boardId")Long boardId,@AuthenticationPrincipal String email ){
        ResponseEntity<? super DeleteBoardResponseDto> response = boardService.deleteBoard(boardId,email);
        return response;
    }
}

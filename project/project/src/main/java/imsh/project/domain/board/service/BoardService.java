package imsh.project.domain.board.service;

import imsh.project.domain.board.dto.request.board.PatchBoardRequestDto;
import imsh.project.domain.board.dto.request.board.PostBoardRequestDto;
import imsh.project.domain.board.dto.request.board.PostCommentRequestDto;
import imsh.project.domain.board.dto.response.board.*;
import org.springframework.http.ResponseEntity;

public interface BoardService {
    ResponseEntity<? super GetBoardResponseDto> getBoard(Long boardId);
    ResponseEntity<? super GetFavoriteListResponseDto> getFavoriteList(Long boardId);
    ResponseEntity<? super GetCommentListResponseDto> getCommentList(Long boardId);
    ResponseEntity<? super GetLatestBoardListResponseDto> getLatestBoardList();
    ResponseEntity<? super GetTop3BoardListResponseDto> getTop3BoardList();
    ResponseEntity<? super GetSearchBoardListResponseDto> getSearchBoardList(String searchWord, String preSearchWord);
    ResponseEntity<? super GetUserBoardListResponseDto> getUserBoardList(String email);
    ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequestDto dto, String email);
    ResponseEntity<? super PostCommentResponseDto> postCommnet(PostCommentRequestDto dto, Long boardId, String email);
    ResponseEntity<? super PutFavoriteResponseDto> putFavorite(Long boardId, String email);
    ResponseEntity<? super PatchBoardResponseDto> patchBoard(PatchBoardRequestDto dto, Long boardId, String email);
    ResponseEntity<? super IncreaseViewCountResponseDto> increaseViewCount(Long boardId);
    ResponseEntity<? super DeleteBoardResponseDto> deleteBoard(Long boardId, String email);

}

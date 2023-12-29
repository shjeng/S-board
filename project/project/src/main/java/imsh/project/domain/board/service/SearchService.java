package imsh.project.domain.board.service;

import imsh.project.domain.board.dto.response.search.GetPopularListResponseDto;
import imsh.project.domain.board.dto.response.search.GetRelationListResponseDto;
import org.springframework.http.ResponseEntity;

public interface SearchService {
    ResponseEntity<? super GetPopularListResponseDto> getPopularList();
    ResponseEntity<? super GetRelationListResponseDto> getRelationList(String searchWord);
}

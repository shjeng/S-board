package imsh.project.domain.board.service.implement;

import imsh.project.domain.board.dto.response.ResponseDto;
import imsh.project.domain.board.dto.response.search.GetPopularListResponseDto;
import imsh.project.domain.board.dto.response.search.GetRelationListResponseDto;
import imsh.project.domain.board.repository.resultSet.GetPopularListResultSet;
import imsh.project.domain.board.repository.resultSet.GetRelationListResultSet;
import imsh.project.domain.board.service.SearchService;
import imsh.project.domain.board.service.repositoryService.SearchLogRepositoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchServiceImplement implements SearchService {

    private final SearchLogRepositoryService searchLogRepositoryService;
    @Override
    public ResponseEntity<? super GetPopularListResponseDto> getPopularList() {
        List<GetPopularListResultSet> resultSets = new ArrayList<>();
        try {
            resultSets = searchLogRepositoryService.getPopularListResultSets();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetPopularListResponseDto.success(resultSets);
    }

    @Override
    public ResponseEntity<? super GetRelationListResponseDto> getRelationList(String searchWord) {
        List<GetRelationListResultSet> resultSets = new ArrayList<>();
        try{
            resultSets = searchLogRepositoryService.getRelationListResultSets(searchWord);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetRelationListResponseDto.success(resultSets);
    }
}

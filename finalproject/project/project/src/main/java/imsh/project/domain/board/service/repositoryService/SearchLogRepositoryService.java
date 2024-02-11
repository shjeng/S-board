package imsh.project.domain.board.service.repositoryService;

import imsh.project.domain.board.entity.SearchLog;
import imsh.project.domain.board.repository.SearchLogRepository;
import imsh.project.domain.board.repository.resultSet.GetPopularListResultSet;
import imsh.project.domain.board.repository.resultSet.GetRelationListResultSet;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class SearchLogRepositoryService {
    private final SearchLogRepository searchLogRepository;

    public List<GetPopularListResultSet> getPopularListResultSets(){
        return searchLogRepository.getPopularList(PageRequest.of(0,16));
    }
    @Transactional
    public void save(SearchLog searchLog){
        searchLogRepository.save(searchLog);
    }

    public List<GetRelationListResultSet> getRelationListResultSets(String searchWord){
        return searchLogRepository.getRelationList(searchWord,PageRequest.of(0,16));
    }
}

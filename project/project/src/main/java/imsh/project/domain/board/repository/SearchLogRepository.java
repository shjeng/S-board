package imsh.project.domain.board.repository;

import imsh.project.domain.board.entity.SearchLog;
import imsh.project.domain.board.repository.resultSet.GetPopularListResultSet;
import imsh.project.domain.board.repository.resultSet.GetRelationListResultSet;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SearchLogRepository extends JpaRepository<SearchLog,Long> {

    @Query(value = "SELECT" +
            " new imsh.project.domain.board.repository.resultSet.GetPopularListResultSet(" +
            "s.searchWord, count(s.searchWord) as count ) from SearchLog s where s.relation = false GROUP BY s.searchWord " +
            "ORDER BY count(s.searchWord) DESC " )
    List<GetPopularListResultSet> getPopularList(Pageable pageable);

    @Query(value = "SELECT " +
            " new imsh.project.domain.board.repository.resultSet.GetRelationListResultSet(" +
            "s.relationWord, count(s.relationWord) as count) FROM SearchLog s " +
            "WHERE s.searchWord =:searchWord AND s.relation = true " +
            "GROUP BY s.relationWord " +
            "ORDER BY count(s.relationWord) DESC")
    List<GetRelationListResultSet> getRelationList(@Param("searchWord") String searchWord, Pageable pageable);
    // pageble: 리미트 걸어주려고 넣음

}

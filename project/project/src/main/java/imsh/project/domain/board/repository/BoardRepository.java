package imsh.project.domain.board.repository;

import imsh.project.domain.board.entity.Board;
import imsh.project.domain.board.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;
public interface BoardRepository extends JpaRepository<Board,Long> {

    void delete(Board entity);

    List<Board> findAllByOrderByWriteDatetimeDesc();
    List<Board> findTop3ByWriteDatetimeGreaterThanOrderByFavoriteCountDescCommentCountDescViewCountDescWriteDatetimeDesc(String writeDatetime);
    //findTop3: 검색 결과 중 상위 3개의 엔터티를 반환합니다.
    //ByWriteDatetimeGreaterThan: writeDatetime 필드가 주어진 값보다 큰(나중인) 엔터티를 검색합니다.
    //OrderBy: 정렬 기준을 지정합니다.
    //FavoriteCountDesc: favoriteCount 필드를 내림차순으로 정렬합니다.
    //CommentCountDesc: commentCount 필드를 내림차순으로 정렬합니다.
    //ViewCountDesc: viewCount 필드를 내림차순으로 정렬합니다.
    //WriteDatetimeDesc: writeDatetime 필드를 내림차순으로 정렬합니다.

    @Query(value = "SELECT b FROM Board b WHERE b.title LIKE %:title% OR b.content LIKE %:content% ORDER BY writeDatetime DESC ")
    List<Board> findBoardBytitleAndContent(@Param("title") String title, @Param("content") String content);

    List<Board> findBoardByUserEntityOrderByWriteDatetimeDesc(UserEntity userEntity);
}

package imsh.project.domain.board.repository;

import imsh.project.domain.board.entity.Board;
import imsh.project.domain.board.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<Favorite,Long> {
    Optional<Favorite> findFavoriteByUserEmailAndBoard(String userEmail, Board board);

    List<Favorite> findAllByBoardId(Long boardId);

    void deleteAllByBoard(Board board);
}

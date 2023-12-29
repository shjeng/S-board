package imsh.project.domain.board.repository;

import imsh.project.domain.board.entity.Board;
import imsh.project.domain.board.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    List<Comment> findAllByBoardOrderByWriteDatetimeDesc(Board board);
    void deleteAllByBoard(Board board);
}

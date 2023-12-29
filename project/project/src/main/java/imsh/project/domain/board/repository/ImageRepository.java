package imsh.project.domain.board.repository;

import imsh.project.domain.board.entity.Board;
import imsh.project.domain.board.entity.Image;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image,Long> {
    List<Image> findAllByBoard(Board board);

    List<Image> findImageByBoard(Board board, Pageable pageable);

    void deleteAllByBoard(Board board);
}

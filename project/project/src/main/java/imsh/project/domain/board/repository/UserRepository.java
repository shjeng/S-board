package imsh.project.domain.board.repository;

import imsh.project.domain.board.dto.object.FavoriteListItem;
import imsh.project.domain.board.entity.Board;
import imsh.project.domain.board.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<UserEntity,Long> {
    boolean existsByEmail(String email);
    boolean existsByNickname(String nickname);
    boolean existsByTelNumber(String telNumber);
    Optional<UserEntity> findUserEntityByEmail(String email);
}

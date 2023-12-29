package imsh.project.domain.board.service.repositoryService;

import imsh.project.domain.board.entity.Board;
import imsh.project.domain.board.entity.Favorite;
import imsh.project.domain.board.repository.FavoriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FavoriteRepositoryService {
    private final FavoriteRepository favoriteRepository;

    public Optional<Favorite> findFavoriteByUserEmailAndBoard(String userEmail, Board board){
        return favoriteRepository.findFavoriteByUserEmailAndBoard(userEmail,board);
    }
    @Transactional
    public void save(Favorite favorite){
        favoriteRepository.save(favorite);
    }
    @Transactional
    public void delete(Favorite favorite){
        favoriteRepository.delete(favorite);
    }
    public List<Favorite> findAllByBoard(Long boardId){
        return favoriteRepository.findAllByBoardId(boardId);
    }

    @Transactional
    public void deleteALLByBoard(Board board){favoriteRepository.deleteAllByBoard(board);}
}

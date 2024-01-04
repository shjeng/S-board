package imsh.project.domain.board.service.repositoryService;

import imsh.project.domain.board.entity.Board;
import imsh.project.domain.board.entity.UserEntity;
import imsh.project.domain.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardRepositoryService {
    private final BoardRepository boardRepository;

    @Transactional
    public void save(Board board){boardRepository.save(board);}
    public Optional<Board> findById(Long id){
        return boardRepository.findById(id);
    }

    @Transactional
    public void delete(Board board) {boardRepository.delete(board);}
    public boolean existsByBoardId(Long boardId){return boardRepository.existsById(boardId);}

    public List<Board> findByOrderByWriteDatetimeDesc(){
        return boardRepository.findAllByOrderByWriteDatetimeDesc();
    }
    public List<Board> findTop3Board(String writeDatetime){
        return boardRepository.findTop3ByWriteDatetimeGreaterThanOrderByFavoriteCountDescCommentCountDescViewCountDescWriteDatetimeDesc(writeDatetime);
    }

    public List<Board> findBySearchKeyword(String title, String content){
        return boardRepository.findBoardBytitleAndContent(title,content);
    }
    public List<Board> findBoardByUserEntity(UserEntity userEntity){
        return boardRepository.findBoardByUserEntityOrderByWriteDatetimeDesc(userEntity);
    }

}

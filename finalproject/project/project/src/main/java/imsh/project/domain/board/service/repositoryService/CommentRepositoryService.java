package imsh.project.domain.board.service.repositoryService;

import imsh.project.domain.board.entity.Board;
import imsh.project.domain.board.entity.Comment;
import imsh.project.domain.board.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CommentRepositoryService {
    private final CommentRepository commentRepository;

    @Transactional
    public Comment save(Comment comment){
        return commentRepository.save(comment);
    }

    public List<Comment> findAllByBoard(Board board){return commentRepository.findAllByBoardOrderByWriteDatetimeDesc(board);}

    @Transactional
    public void deleteAllByBoard(Board board){commentRepository.deleteAllByBoard(board);}
}

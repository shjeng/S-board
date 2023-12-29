package imsh.project.domain.board.service.repositoryService;

import imsh.project.domain.board.entity.Board;
import imsh.project.domain.board.entity.Image;
import imsh.project.domain.board.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ImageRepositoryService {
    private final ImageRepository imageRepository;

    public List<Image> findImagesByBoard(Board board){
        return imageRepository.findAllByBoard(board);
    }

    public List<Image> findOneByBoard(Board board) {return imageRepository.findImageByBoard(board, PageRequest.of(0,1));}
    @Transactional
    public void saveAll(List<Image>images){
        imageRepository.saveAll(images);
    }

    @Transactional
    public void deleteAllByBoard(Board board){imageRepository.deleteAllByBoard(board);}
}

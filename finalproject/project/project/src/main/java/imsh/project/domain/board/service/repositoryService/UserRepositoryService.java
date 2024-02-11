package imsh.project.domain.board.service.repositoryService;

import imsh.project.domain.board.dto.object.FavoriteListItem;
import imsh.project.domain.board.entity.Board;
import imsh.project.domain.board.entity.UserEntity;
import imsh.project.domain.board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserRepositoryService {
    private final UserRepository userRepository;

    @Transactional
    public void signUpUser(UserEntity userEntity){
        userRepository.save(userEntity);
    }
    public Optional<UserEntity> findByEmail(String email){return userRepository.findUserEntityByEmail(email);}
    public boolean existedByEmail(String email){return userRepository.existsByEmail(email);}

    public boolean existsByNickname(String nickname){return userRepository.existsByNickname(nickname);}
}

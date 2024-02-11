package imsh.project.domain.board.service.implement;

import imsh.project.domain.board.dto.request.auth.SignInRequestDto;
import imsh.project.domain.board.dto.request.auth.SignUpRequestDto;
import imsh.project.domain.board.dto.response.ResponseDto;
import imsh.project.domain.board.dto.response.auth.SignInResponseDto;
import imsh.project.domain.board.dto.response.auth.SignUpResponseDto;
import imsh.project.domain.board.entity.UserEntity;
import imsh.project.domain.board.repository.UserRepository;
import imsh.project.domain.board.service.AuthService;
import imsh.project.domain.board.service.repositoryService.UserRepositoryService;
import imsh.project.domain.provider.JwtProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImplement implements AuthService {

    private final UserRepository userRepository;
    private final UserRepositoryService userRepositoryService;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final JwtProvider jwtProvider;

    @Override
    public ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto) {
        try {
            String email = dto.getEmail();
            boolean existedEmail = userRepository.existsByEmail(email);
            if(existedEmail) return SignUpResponseDto.duplicateEmail();

            String nickname = dto.getNickname();
            boolean existedNickname = userRepository.existsByNickname(nickname);
            if(existedNickname) return SignUpResponseDto.duplicateNickname();

            String telNumber = dto.getTelNumber();
            boolean existedTelNumber = userRepository.existsByTelNumber(telNumber);
            if(existedTelNumber) return SignUpResponseDto.duplicateTelNumber();

            String password = dto.getPassword();
            String encodedPassword = passwordEncoder.encode(password);
            dto.setPassword(encodedPassword);

            UserEntity userEntity = new UserEntity(dto);
            userRepositoryService.signUpUser(userEntity);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return SignUpResponseDto.success();
    }

    @Override
    public ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto) {
        String token = null;
        try{
            String email = dto.getEmail();
            Optional<UserEntity> userOptional = userRepository.findUserEntityByEmail(email);
            if(userOptional.isEmpty()) return SignInResponseDto.signInFailed(); // 이메일에 해당하는 아이디가 없을 경우

            UserEntity userEntity = userOptional.get();
            String password = dto.getPassword();
            String encodedPassword = userEntity.getPassword();
            boolean isMatched = passwordEncoder.matches(password,encodedPassword);
            if(!isMatched) return  SignInResponseDto.signInFailed();
            token = jwtProvider.create(email);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return SignInResponseDto.success(token);
    }
}

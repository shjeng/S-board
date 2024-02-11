package imsh.project.domain.board.service.implement;

import imsh.project.domain.board.dto.request.User.PatchNicknameRequestDto;
import imsh.project.domain.board.dto.response.ResponseDto;
import imsh.project.domain.board.dto.response.user.*;
import imsh.project.domain.board.entity.UserEntity;
import imsh.project.domain.board.service.UserService;
import imsh.project.domain.board.service.repositoryService.UserRepositoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImplement implements UserService {

    private final UserRepositoryService userRepositoryService;

    @Override
    public ResponseEntity<? super GetUserResponseDto> getUser(String email) {
        UserEntity userEntity = null;
        try{
            Optional<UserEntity> userEntityOptional = userRepositoryService.findByEmail(email);
            // 유저를 좀 가지고 와줘라
            if(userEntityOptional.isEmpty()) return GetUserResponseDto.noExistUser();
            userEntity = userEntityOptional.get();

        }catch (Exception e){
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetUserResponseDto.success(userEntity);
    }

    @Override
    public ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(String email) {
        UserEntity userEntity = null;
        try{
            Optional<UserEntity> user = userRepositoryService.findByEmail(email);
            if(user.isEmpty()) return GetSignInUserResponseDto.notExistUser();
            userEntity = user.get();

        }catch (Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetSignInUserResponseDto.success(userEntity);
    }

    @Override
    public ResponseEntity<? super PatchNicknameResponseDto> patchNickname(PatchNicknameRequestDto dto,String email) {
        try{
            Optional<UserEntity> userEntityOptional = userRepositoryService.findByEmail(email);
            if(userEntityOptional.isEmpty()) return PatchNicknameResponseDto.noExistUser();
            UserEntity userEntity = userEntityOptional.get();

            String nickname = dto.getNickname();
            boolean existedNickname = userRepositoryService.existsByNickname(nickname);
            if(existedNickname) return PatchNicknameResponseDto.duplicateNickname();

            userEntity.setNickname(nickname);
            userRepositoryService.signUpUser(userEntity);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return PatchNicknameResponseDto.success();
    }

    @Override
    public ResponseEntity<? super PatchProfileImageResponseDto> patchProfileImage(PatchProfileImageRequestDto dto, String email) {
        try {
            Optional<UserEntity> userEntityOptional = userRepositoryService.findByEmail(email);
            if(userEntityOptional.isEmpty()) return PatchNicknameResponseDto.noExistUser();
            UserEntity userEntity = userEntityOptional.get();

            String profileImage = dto.getProfileImage();
            userEntity.setProfileImage(profileImage);
            userRepositoryService.signUpUser(userEntity);

        } catch (Exception e){
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return PatchProfileImageResponseDto.success();
    }
}

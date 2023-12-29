package imsh.project.domain.board.service;

import imsh.project.domain.board.dto.object.FavoriteListItem;
import imsh.project.domain.board.dto.request.User.PatchNicknameRequestDto;
import imsh.project.domain.board.dto.response.user.*;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<? super GetUserResponseDto> getUser(String email);
    ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(String email);

    ResponseEntity<? super PatchNicknameResponseDto> patchNickname(PatchNicknameRequestDto dto, String email);
    ResponseEntity<? super PatchProfileImageResponseDto> patchProfileImage(PatchProfileImageRequestDto dto, String email);

}

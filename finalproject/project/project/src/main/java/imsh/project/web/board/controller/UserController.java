package imsh.project.web.board.controller;

import imsh.project.domain.board.dto.request.User.PatchNicknameRequestDto;
import imsh.project.domain.board.dto.response.user.*;
import imsh.project.domain.board.service.UserService;
import imsh.project.domain.board.service.repositoryService.UserRepositoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    private final UserRepositoryService userRepositoryService;
    @GetMapping("/{email}") // 유저 정보 불러오기
    public ResponseEntity<? super GetUserResponseDto> getUser(@PathVariable("email") String email){
        ResponseEntity<? super GetUserResponseDto> response = userService.getUser(email);
        return response;
    }

    @GetMapping("") // 마이페이지
    public ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(@AuthenticationPrincipal String email){
        ResponseEntity<? super GetSignInUserResponseDto> response = userService.getSignInUser(email);
        return response;
    }
    @PatchMapping("nickname") // 닉네임 수정
    public ResponseEntity<? super PatchNicknameResponseDto> patchNickname(
            @RequestBody @Valid PatchNicknameRequestDto requestBody,
            @AuthenticationPrincipal String email){
        ResponseEntity<? super PatchNicknameResponseDto> response = userService.patchNickname(requestBody,email);
        return response;
    }
    @PatchMapping("profile")
    public ResponseEntity<? super PatchProfileImageResponseDto> patchProfileImage(
            @RequestBody @Valid PatchProfileImageRequestDto requestBody,
            @AuthenticationPrincipal String email){
        ResponseEntity<? super PatchProfileImageResponseDto> response = userService.patchProfileImage(requestBody,email);
        return response;
    }
}

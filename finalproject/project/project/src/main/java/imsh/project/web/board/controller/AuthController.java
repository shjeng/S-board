package imsh.project.web.board.controller;

import imsh.project.domain.board.dto.request.auth.SignInRequestDto;
import imsh.project.domain.board.dto.request.auth.SignUpRequestDto;
import imsh.project.domain.board.dto.response.auth.SignInResponseDto;
import imsh.project.domain.board.dto.response.auth.SignUpResponseDto;
import imsh.project.domain.board.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-up")
    public ResponseEntity<? super SignUpResponseDto> signUp(
        @RequestBody @Valid SignUpRequestDto requestBody){
        ResponseEntity <? super SignUpResponseDto> response = authService.signUp(requestBody);
        return response; // 원래는 페이지를 넘겨줬는데
    }

    @PostMapping("/sign-in")
    public ResponseEntity<? super SignInResponseDto> signIn(
            @RequestBody @Valid SignInRequestDto requestBody){
        System.out.println("이메일 =" + requestBody.getEmail());
        ResponseEntity<? super SignInResponseDto> response = authService.signIn(requestBody);
        return response;
    }
}

package imsh.project.domain.board.service;

import imsh.project.domain.board.dto.request.auth.SignInRequestDto;
import imsh.project.domain.board.dto.request.auth.SignUpRequestDto;
import imsh.project.domain.board.dto.response.auth.SignInResponseDto;
import imsh.project.domain.board.dto.response.auth.SignUpResponseDto;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto);
    ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto);
}

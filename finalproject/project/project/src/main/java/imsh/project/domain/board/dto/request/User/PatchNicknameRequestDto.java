package imsh.project.domain.board.dto.request.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class PatchNicknameRequestDto {
    @NotBlank
    private String nickname;

}

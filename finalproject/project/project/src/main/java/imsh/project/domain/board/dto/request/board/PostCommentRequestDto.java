package imsh.project.domain.board.dto.request.board;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class PostCommentRequestDto {

    @NotBlank
    private String content;

}

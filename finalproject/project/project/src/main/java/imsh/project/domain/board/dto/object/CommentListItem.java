package imsh.project.domain.board.dto.object;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentListItem {
    private String nickname; // userEntity에서
    private String profileImage; // userEntity에서
    private String writeDatetime; // comment
    private String content; // comment
}

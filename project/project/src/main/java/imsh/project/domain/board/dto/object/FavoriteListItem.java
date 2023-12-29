package imsh.project.domain.board.dto.object;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FavoriteListItem {

    private String email;
    private String nickname;
    private String profileImage;

}

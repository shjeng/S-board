package imsh.project.domain.board.repository.resultSet;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetPopularListResultSet {

    private String searchWord;
    private Long count;

//    private int count;
}

package imsh.project.domain.board.dto.object;

import imsh.project.domain.board.entity.Board;
import imsh.project.domain.board.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardListItem {
    private Long boardNumber;
    private String title;
    private String content;
    private String boardTitleImage;
    private int favoriteCount;
    private int commentCount;
    private Long viewCount;
    private String writeDatetime;
    private String writerNickname;
    private String writerProfileImage;

    public BoardListItem(Board board, UserEntity user, String image){
        this.boardNumber = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.favoriteCount = board.getFavoriteCount();
        this.commentCount = board.getCommentCount();
        this.viewCount = board.getViewCount();
        this.writeDatetime = board.getWriteDatetime();
        this.writerNickname = user.getNickname();
        this.writerProfileImage = user.getProfileImage();
        this.boardTitleImage = image;
    }
//    public static List<BoardListItem> getList(List<BoardListView> boardListViews){
//        List<BoardListItem> list = new ArrayList<>();
//        boardListViews.forEach(boardListView -> list.add(new BoardListItem(boardListView)));
//        return list;
//    }
}

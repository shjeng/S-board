package imsh.project.domain.board.entity;

import imsh.project.domain.board.dto.request.board.PatchBoardRequestDto;
import imsh.project.domain.board.dto.request.board.PostBoardRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Board {
    @Id @GeneratedValue
    @Column(name="board_id")
    private Long id;

    // 게시물 제목
    @Column(name="board_title")
    private String title;

    // 게시물 내용
    @Column(name="board_content")
    private String content;

    // 게시물 작성 날짜
    @Column(name="board_write_date_time")
    private String writeDatetime;

    @Column(name="board_favorite_count")
    private Integer favoriteCount;
    // 게시물 조회 수
    @Column(name="board_view_count")
    private Long viewCount;

    // 사용자 이메일
    @Column(name="board_writer_email")
    private String writerEmail;

    @Column(name="board_comment_count")
    private Integer commentCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    // 게시물 댓글 수
    @OneToMany(mappedBy = "board")
    private List<Comment> comments;

    @OneToMany(mappedBy = "board")
    private List<Image> images;

    @OneToMany(mappedBy = "board")
    private List<Favorite> favorites;

    public Board(PostBoardRequestDto dto, String writerEmail, UserEntity userEntity) {
        Date now = Date.from(Instant.now());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String writeDatetime = simpleDateFormat.format(now);
        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.writeDatetime = writeDatetime;
        this.viewCount = 0L;
        this.favoriteCount = 0;
        this.commentCount = 0;
        this.writerEmail = writerEmail;
        this.userEntity = userEntity;
    }
    public Board increaseView(){
        this.viewCount++;
        return this;
    }
    public Board increaseFavoriteCount(){
        this.favoriteCount++;
        return this;
    }
    public Board decreaseFavoriteCount(){
        this.favoriteCount--;
        return this;
    }

    public Board increaseCommentCount(){
        this.commentCount++;
        return this;
    }

    public void patchBoard(PatchBoardRequestDto dto){
        this.title = dto.getTitle();
        this.content = dto.getContent();
    }
}

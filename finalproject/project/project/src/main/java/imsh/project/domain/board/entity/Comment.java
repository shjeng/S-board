package imsh.project.domain.board.entity;

import imsh.project.domain.board.dto.request.board.PostCommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

@Entity
@NoArgsConstructor
@Getter
public class Comment {
    // 댓글 번호
    @Id @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    // 댓글 내용
    @Column(name="comment_content")
    private String content;

    // 사용자 이메일
    @Column(name="comment_email")
    private String email;

    // 게시물 번호
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="board_id")
    private Board board;

    // 댓글 작성 날짜 및 시간
    @Column(name="comment_write_datetime")
    private String writeDatetime;

    public Comment(PostCommentRequestDto dto, Board board, String email) {

        Date now = Date.from(Instant.now());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String writeDatetime = simpleDateFormat.format(now);
        System.out.println("now Time : " + writeDatetime );

        this.content = dto.getContent();
        this.writeDatetime = writeDatetime;
        this.email = email;
        this.board = board;
    }
}

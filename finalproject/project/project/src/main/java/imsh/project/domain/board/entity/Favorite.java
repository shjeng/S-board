package imsh.project.domain.board.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class Favorite {

    @Id @GeneratedValue
    @Column(name="favorite_id")
    private Long id;

    // 사용자 이메일
    @Column(name="favorite_user_email")
    private String userEmail;

    // 게시물 번호
    @ManyToOne
    @JoinColumn(name="board_id")
    private Board board;

    public Favorite(String userEmail, Board board) {
        this.userEmail = userEmail;
        this.board = board;
    }
}

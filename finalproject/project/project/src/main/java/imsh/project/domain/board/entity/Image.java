package imsh.project.domain.board.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Image { // 게시물 이미지

    @Id @GeneratedValue
    @Column(name="image_id")
    private Long id;

    // 게시물 번호
    @ManyToOne
    @JoinColumn(name="board_id")
    private Board board;

    // 게시물 이미지 URL
    private String image;

    public Image(Board board, String image){
        this.board = board;
        this.image = image;
    }
}

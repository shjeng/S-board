package imsh.project.domain.board.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SearchLog {

    @Id @GeneratedValue
    @Column(name="search_log_id")
    private Long id;
    // 검색어
    @Column(name="search_log_word")
    private String searchWord;
    // 관련 검색어
    @Column(name="search_log_relation_word")
    private String relationWord;
    // 관련 검색어 여부
    @Column(name="search_log_relation")
    private Boolean relation;

    public SearchLog(String searchWord, String relationWord, boolean relation){
        this.searchWord = searchWord;
        this.relationWord = relationWord;
        this.relation = relation;
    }
}

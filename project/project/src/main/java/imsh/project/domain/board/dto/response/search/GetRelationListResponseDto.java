package imsh.project.domain.board.dto.response.search;

import imsh.project.domain.board.dto.response.ResponseDto;
import imsh.project.domain.board.repository.resultSet.GetRelationListResultSet;
import imsh.project.domain.common.ResponseCode;
import imsh.project.domain.common.ResponseMessage;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@Getter
public class GetRelationListResponseDto extends ResponseDto {

    private List<String> relativeWordList;
    private GetRelationListResponseDto(List<GetRelationListResultSet> resultSets){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        List<String> relativeWordList = new ArrayList<>();
        resultSets.forEach(resultSet -> {
            relativeWordList.add(resultSet.getRelationWord());
        });
        this.relativeWordList = relativeWordList;
    }
    public static ResponseEntity<GetRelationListResponseDto> success(List<GetRelationListResultSet> resultSets){
        GetRelationListResponseDto result = new GetRelationListResponseDto(resultSets);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}

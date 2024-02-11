package imsh.project.domain.board.dto.response.board;

import imsh.project.domain.board.dto.object.BoardListItem;
import imsh.project.domain.board.dto.response.ResponseDto;
import imsh.project.domain.common.ResponseCode;
import imsh.project.domain.common.ResponseMessage;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
public class GetSearchBoardListResponseDto extends ResponseDto {
    private List<BoardListItem> searchList;
    private GetSearchBoardListResponseDto(List<BoardListItem> searchList) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.searchList = searchList;
    }

    public static ResponseEntity<GetSearchBoardListResponseDto> success(List<BoardListItem> searchList){
        GetSearchBoardListResponseDto result = new GetSearchBoardListResponseDto(searchList);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}

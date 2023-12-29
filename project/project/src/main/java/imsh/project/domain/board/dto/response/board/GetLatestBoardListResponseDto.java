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
public class GetLatestBoardListResponseDto extends ResponseDto {

    private List<BoardListItem> latestList;
    private GetLatestBoardListResponseDto(List<BoardListItem> latestList){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.latestList = latestList;
    }
    public static ResponseEntity<GetLatestBoardListResponseDto> success(List<BoardListItem> latestList){
        GetLatestBoardListResponseDto result = new GetLatestBoardListResponseDto(latestList);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}

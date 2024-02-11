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
public class GetUserBoardListResponseDto extends ResponseDto {
    private List<BoardListItem> userBoardList;
    private GetUserBoardListResponseDto(List<BoardListItem> userBoardList){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.userBoardList = userBoardList;
    }
    public static ResponseEntity<GetUserBoardListResponseDto> success(List<BoardListItem> userBoardList){
        GetUserBoardListResponseDto result = new GetUserBoardListResponseDto(userBoardList);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    public static ResponseEntity<ResponseDto> noExistUser(){
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_USER,ResponseMessage.NOT_EXISTED_USER);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
}

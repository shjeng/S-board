package imsh.project.domain.board.dto.response.board;

import imsh.project.domain.board.dto.response.ResponseDto;
import imsh.project.domain.common.ResponseCode;
import imsh.project.domain.common.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class IncreaseViewCountResponseDto extends ResponseDto {

    private IncreaseViewCountResponseDto(){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }
    public static ResponseEntity<IncreaseViewCountResponseDto> success(){
        IncreaseViewCountResponseDto result = new IncreaseViewCountResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    public static ResponseEntity<ResponseDto> notExistBoard(){
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_BOARD,ResponseMessage.NOT_EXISTED_BOARD);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
}

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
public class GetTop3BoardListResponseDto extends ResponseDto {
    private List<BoardListItem> top3List;

    private GetTop3BoardListResponseDto(List<BoardListItem> top3List){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.top3List = top3List;
    }

    public static ResponseEntity<GetTop3BoardListResponseDto> success(List<BoardListItem> top3List){
        GetTop3BoardListResponseDto result = new GetTop3BoardListResponseDto(top3List);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}

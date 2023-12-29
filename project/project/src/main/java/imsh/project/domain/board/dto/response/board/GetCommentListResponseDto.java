package imsh.project.domain.board.dto.response.board;

import imsh.project.domain.board.dto.object.CommentListItem;
import imsh.project.domain.board.dto.response.ResponseDto;
import imsh.project.domain.common.ResponseCode;
import imsh.project.domain.common.ResponseMessage;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
public class GetCommentListResponseDto extends ResponseDto {
    private List<CommentListItem> commentList;

    private GetCommentListResponseDto(List<CommentListItem> commentList){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.commentList = commentList;
    }
    public static ResponseEntity<GetCommentListResponseDto> success(List<CommentListItem> commentList){
        GetCommentListResponseDto result = new GetCommentListResponseDto(commentList);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    public static ResponseEntity<ResponseDto> noExistBoard(){
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_BOARD,ResponseMessage.NOT_EXISTED_BOARD);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
}

package imsh.project.domain.board.dto.response.board;

import imsh.project.domain.board.dto.response.ResponseDto;
import imsh.project.domain.board.entity.Image;
import imsh.project.domain.common.ResponseCode;
import imsh.project.domain.common.ResponseMessage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
@Builder
public class GetBoardResponseDto extends ResponseDto {
    private Long boardNumber;
    private String title;
    private String content;
    private List<String> boardImageList;
    private String writeDatetime;
    private String writerEmail;
    private String writerNickname;
    private String writerProfileImage;


    public static ResponseEntity<GetBoardResponseDto> success(GetBoardResponseDto result){
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    public GetBoardResponseDto(){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }
    public GetBoardResponseDto(Long boardNumber, String title, String content, List<String> boardImageList, String writeDatetime, String writerEmail, String writerNickname, String writerProfileImage) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.boardNumber = boardNumber;
        this.title = title;
        this.content = content;
        this.boardImageList = boardImageList;
        this.writeDatetime = writeDatetime;
        this.writerEmail = writerEmail;
        this.writerNickname = writerNickname;
        this.writerProfileImage = writerProfileImage;
    }

    public static ResponseEntity<ResponseDto> notExistBoard(){
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_BOARD,ResponseMessage.NOT_EXISTED_BOARD);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
    public static ResponseEntity<ResponseDto> notExistUser(){
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_USER,ResponseMessage.NOT_EXISTED_USER);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

}

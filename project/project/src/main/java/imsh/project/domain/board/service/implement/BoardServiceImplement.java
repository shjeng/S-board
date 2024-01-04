package imsh.project.domain.board.service.implement;

import imsh.project.domain.board.dto.object.BoardListItem;
import imsh.project.domain.board.dto.object.CommentListItem;
import imsh.project.domain.board.dto.object.FavoriteListItem;
import imsh.project.domain.board.dto.request.board.PatchBoardRequestDto;
import imsh.project.domain.board.dto.request.board.PostBoardRequestDto;
import imsh.project.domain.board.dto.request.board.PostCommentRequestDto;
import imsh.project.domain.board.dto.response.ResponseDto;
import imsh.project.domain.board.dto.response.board.*;
import imsh.project.domain.board.entity.*;
import imsh.project.domain.board.repository.UserRepository;
import imsh.project.domain.board.service.BoardService;
import imsh.project.domain.board.service.repositoryService.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

// localhost:8081
@Service
@RequiredArgsConstructor
public class BoardServiceImplement implements BoardService {

    @Value("${file.path}")
    private String filePath;

    private final UserRepository userRepository;
    private final UserRepositoryService userRepositoryService;
    private final BoardRepositoryService boardRepositoryService;
    private final ImageRepositoryService imageRepositoryService;
    private final CommentRepositoryService commentRepositoryService;
    private final SearchLogRepositoryService searchLogRepositoryService;
    private final FavoriteRepositoryService favoriteRepositoryService;

    @Override
    public ResponseEntity<? super GetBoardResponseDto> getBoard(Long boardId) {
        GetBoardResponseDto result = null;
        List<Image> imageEntites = null;
        List<String> imageDto = new ArrayList<>();
        try{
            Optional<Board> boardOptional = boardRepositoryService.findById(boardId);
            if(boardOptional.isEmpty()) return GetBoardResponseDto.notExistBoard();
            Board board = boardOptional.get();

            Optional<UserEntity> userEntityOptional = userRepository.findUserEntityByEmail(board.getWriterEmail());
            if(userEntityOptional.isEmpty()) return GetBoardResponseDto.notExistUser();
            UserEntity userEntity = userEntityOptional.get();

            imageEntites = imageRepositoryService.findImagesByBoard(board);
            imageEntites.forEach(image -> imageDto.add(image.getImage()));
            result = GetBoardResponseDto.builder()
                    .boardNumber(board.getId())
                    .title(board.getTitle())
                    .content(board.getContent())
                    .writeDatetime(board.getWriteDatetime())
                    .writerEmail(board.getWriterEmail())
                    .writerNickname(userEntity.getNickname())
                    .writerProfileImage(userEntity.getProfileImage())
                    .boardImageList(imageDto)
                    .build();
        } catch (Exception e){
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetBoardResponseDto.success(result);
    }
    // 게시물 상세보기

    @Override
    public ResponseEntity<? super GetFavoriteListResponseDto> getFavoriteList(Long boardId) {
        List<FavoriteListItem> favoriteListItems = new ArrayList<>();
        try{
            Optional<Board> boardOptional = boardRepositoryService.findById(boardId);
            if(boardOptional.isEmpty()) return GetFavoriteListResponseDto.noExistBoard();

            FavoriteListItem favoriteListItem;
            UserEntity userEntity;
            List<Favorite> favoritesByBoardId = favoriteRepositoryService.findAllByBoard(boardId);
            for (Favorite favorite : favoritesByBoardId) {
                String userEmail = favorite.getUserEmail();
                Optional<UserEntity> userO = userRepositoryService.findByEmail(userEmail);
                if(userO.isEmpty()) throw new Exception("좋아요 목록에 존재하지 않는 계정이 있습니다.");
                userEntity = userO.get();
                favoriteListItem = FavoriteListItem.builder()
                        .email(userEntity.getEmail())
                        .nickname(userEntity.getNickname())
                        .profileImage(userEntity.getProfileImage())
                        .build();
                favoriteListItems.add(favoriteListItem);
            }

        }catch (Exception e){
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetFavoriteListResponseDto.success(favoriteListItems);
    }
    // 좋아요 목록

    @Override
    public ResponseEntity<? super GetCommentListResponseDto> getCommentList(Long boardId) {
        List<CommentListItem> result = new ArrayList<>();
        try{
            Optional<Board> boardOptional = boardRepositoryService.findById(boardId);
            if(boardOptional.isEmpty()) return GetCommentListResponseDto.noExistBoard();
            Board board = boardOptional.get();

            List<Comment> commentsByBoard = commentRepositoryService.findAllByBoard(board);
            for (Comment comment: commentsByBoard) {
                Optional<UserEntity> userEntityOptional = userRepositoryService.findByEmail(comment.getEmail());
                if(userEntityOptional.isEmpty()) return GetCommentListResponseDto.databaseError();
                UserEntity userEntity = userEntityOptional.get();

                CommentListItem commentListItem = CommentListItem.builder()
                        .nickname(userEntity.getNickname())
                        .profileImage(userEntity.getProfileImage())
                        .writeDatetime(comment.getWriteDatetime())
                        .content(comment.getContent())
                        .build();
                result.add(commentListItem);
            }

        }catch (Exception e){
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetCommentListResponseDto.success(result);
    }
    // 댓글 목록


    @Override
    public ResponseEntity<? super GetLatestBoardListResponseDto> getLatestBoardList() {
        List<BoardListItem> boardListItems = new ArrayList<>();
        try{
            List<Board> boards = boardRepositoryService.findByOrderByWriteDatetimeDesc();
            boards.forEach(board -> {
                String imageString = null;
                List<Image> images = imageRepositoryService.findOneByBoard(board);
                if(!images.isEmpty()) imageString = images.get(0).getImage();

                Optional<UserEntity> userEntityOptional = userRepositoryService.findByEmail(board.getWriterEmail());
                UserEntity userEntity = userEntityOptional.get();
                BoardListItem boardListItem = new BoardListItem(board,userEntity, imageString);
                boardListItems.add(boardListItem);
            });
        }catch (Exception e){
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetLatestBoardListResponseDto.success(boardListItems);
    }
    // 최신 게시물 불러오기


    @Override
    public ResponseEntity<? super GetTop3BoardListResponseDto> getTop3BoardList() {
        List<BoardListItem> boardListItems = new ArrayList<>();
        try {
            Date beforeWeek = Date.from(Instant.now().minus(7, ChronoUnit.DAYS));
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String sevenDaysAgo = simpleDateFormat.format(beforeWeek);
            List<Board> boards = boardRepositoryService.findTop3Board(sevenDaysAgo);
            boards.forEach(board -> {
                String imageString = null;
                List<Image> images = imageRepositoryService.findOneByBoard(board);
                if(!images.isEmpty()) imageString = images.get(0).getImage();
                Optional<UserEntity> userEntityOptional = userRepositoryService.findByEmail(board.getWriterEmail());
                UserEntity userEntity = userEntityOptional.get();
                BoardListItem boardListItem = new BoardListItem(board,userEntity, imageString);
                boardListItems.add(boardListItem);
            });
        }catch (Exception e){
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetTop3BoardListResponseDto.success(boardListItems);
    }
    // 탑3 게시물


    @Override
    public ResponseEntity<? super GetSearchBoardListResponseDto> getSearchBoardList(String searchWord, String preSearchWord) {
        List<BoardListItem> boardListItems = new ArrayList<>();
        try{
            List<Board> boards = boardRepositoryService.findBySearchKeyword(searchWord,searchWord);
            boards.forEach(board -> {
                UserEntity userEntity = userRepositoryService.findByEmail(board.getWriterEmail()).get();
                Image image = imageRepositoryService.findOneByBoard(board).get(0);
                BoardListItem boardListItem = new BoardListItem(board,userEntity,image.getImage());
                boardListItems.add(boardListItem);
            });
            SearchLog searchLog =new SearchLog(searchWord, preSearchWord,false);
            searchLogRepositoryService.save(searchLog);

            boolean relation = preSearchWord != null;
            if(relation){
                searchLog = new SearchLog(preSearchWord,searchWord,relation);
                searchLogRepositoryService.save(searchLog);
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetSearchBoardListResponseDto.success(boardListItems);
    }
    // 검색 키워드로 게시물 가져오기


    @Override
    public ResponseEntity<? super GetUserBoardListResponseDto> getUserBoardList(String email) {
        List<BoardListItem> boardListItems = new ArrayList<>();
        try{
            Optional<UserEntity> userEntityOptional = userRepositoryService.findByEmail(email);
            if(userEntityOptional.isEmpty()) return GetUserBoardListResponseDto.noExistUser();
            UserEntity userEntity = userEntityOptional.get();

            List<Board> boards = boardRepositoryService.findBoardByUserEntity(userEntity);
            boards.forEach(board -> {
                String img = null;
                List<Image> oneByBoard = imageRepositoryService.findOneByBoard(board);
                if(!oneByBoard.isEmpty()) img = oneByBoard.get(0).getImage();
                BoardListItem boardListItem = new BoardListItem(board, userEntity,img);
                boardListItems.add(boardListItem);
            });

        } catch (Exception e){
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetUserBoardListResponseDto.success(boardListItems);
    }
    // 회원 게시물 조회

    @Override
    public ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequestDto dto, String email) {
        try{
            Optional<UserEntity> userEntityOptional = userRepositoryService.findByEmail(email);
            if(userEntityOptional.isEmpty()) return PostBoardResponseDto.notExisteUser();
            UserEntity userEntity = userEntityOptional.get();

            Board boardEntity = new Board(dto, email, userEntity);
            boardRepositoryService.save(boardEntity);
            Long boardEntityId = boardEntity.getId();

            Board board = boardRepositoryService.findById(boardEntityId).get();
            List<String> boardImageList = dto.getBoardImageList();
            List<Image> imageEntities = new ArrayList<>();
            for(String image : boardImageList){
                Image imageEntity = new Image(board,image);
                imageEntities.add(imageEntity);
            }
            imageRepositoryService.saveAll(imageEntities);
        }catch (Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return PostBoardResponseDto.success();
    }
    // 게시물 등록 메서드

    @Override
    public ResponseEntity<? super PostCommentResponseDto> postCommnet(PostCommentRequestDto dto, Long boardId, String email) {
        try{
            Optional<Board> boardOptional = boardRepositoryService.findById(boardId);
            if(boardOptional.isEmpty()) return PostCommentResponseDto.noExistBoard();
            Board board = boardOptional.get();

            boolean existedUser = userRepositoryService.existedByEmail(email);
            if(!existedUser) return PostCommentResponseDto.noExistUser();

            Comment comment = new Comment(dto, board, email);
            commentRepositoryService.save(comment);
            boardRepositoryService.save(board.increaseCommentCount());
        }catch (Exception e){
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return PostCommentResponseDto.success();
    }
    // 댓글 기능

    @Override
    public ResponseEntity<? super PutFavoriteResponseDto> putFavorite(Long boardId, String email) {
        try{
            boolean existedUser = userRepository.existsByEmail(email);
            if(!existedUser) return PutFavoriteResponseDto.noExistUser();

            Optional<Board> boardOptional = boardRepositoryService.findById(boardId);
            if(boardOptional.isEmpty()) return PutFavoriteResponseDto.noExistBoard();
            Board board = boardOptional.get();

            Favorite favorite;
            Optional<Favorite> favoriteOptional = favoriteRepositoryService.findFavoriteByUserEmailAndBoard(email, board);
            if(favoriteOptional.isEmpty()){
                favorite = new Favorite(email,board);
                favoriteRepositoryService.save(favorite);
                boardRepositoryService.save(board.increaseFavoriteCount());
            } else{
                favorite = favoriteOptional.get();
                favoriteRepositoryService.delete(favorite);
                boardRepositoryService.save(board.decreaseFavoriteCount());
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return PutFavoriteResponseDto.success();
    }
    // 좋아요 기능

    @Override
    public ResponseEntity<? super PatchBoardResponseDto> patchBoard(PatchBoardRequestDto dto, Long boardId, String email) {
        try {
            Optional<Board> boardOptional = boardRepositoryService.findById(boardId);
            if(boardOptional.isEmpty()) return PatchBoardResponseDto.notExistBoard();
            Board board = boardOptional.get();

            boolean existedUser = userRepositoryService.existedByEmail(email);
            if(!existedUser) return PatchBoardResponseDto.notExistUser();

            String writerEmail = board.getWriterEmail();
            boolean isWriter = writerEmail.equals(email);
            if(!isWriter) return PatchBoardResponseDto.noPermission();

            List<Image> beforeImgs = imageRepositoryService.findImagesByBoard(board);
            beforeImgs.forEach(img -> {
                String filename = img.getImage().substring(img.getImage().lastIndexOf("/")+1);
                File file = new File(filePath + filename);
                file.delete();
            });

            board.patchBoard(dto);
            boardRepositoryService.save(board);

            imageRepositoryService.deleteAllByBoard(board);
            List<String> boardImageList = dto.getBoardImageList();
            List<Image> images = new ArrayList<>();
            boardImageList.forEach(image-> images.add(new Image(board,image)));
            imageRepositoryService.saveAll(images);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return PatchBoardResponseDto.success();
    }
    // 게시물 수정

    @Override
    public ResponseEntity<? super IncreaseViewCountResponseDto> increaseViewCount(Long boardId) {
        try{
            Optional<Board> boardOptional = boardRepositoryService.findById(boardId);
            if(boardOptional.isEmpty()) return IncreaseViewCountResponseDto.notExistBoard();
            Board board = boardOptional.get().increaseView();
            boardRepositoryService.save(board);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return IncreaseViewCountResponseDto.success();
    }
    // 조회수 증가

    @Override
    public ResponseEntity<? super DeleteBoardResponseDto> deleteBoard(Long boardId, String email) {
        try{
            Optional<UserEntity> userEntityOptional = userRepositoryService.findByEmail(email);
            if(userEntityOptional.isEmpty()) return DeleteBoardResponseDto.noExistUser();

            Optional<Board> boardOptional = boardRepositoryService.findById(boardId);
            if(boardOptional.isEmpty()) return DeleteBoardResponseDto.noExistBoard();
            Board board = boardOptional.get();
            List<Image> beforeImgs = imageRepositoryService.findImagesByBoard(board);
            beforeImgs.forEach(img -> {
                String filename = img.getImage().substring(img.getImage().lastIndexOf("/")+1);
                File file = new File(filePath + filename);
                file.delete();
            });

            if(!board.getWriterEmail().equals(email)) return DeleteBoardResponseDto.noPermission();
            imageRepositoryService.deleteAllByBoard(board);
            commentRepositoryService.deleteAllByBoard(board);
            favoriteRepositoryService.deleteALLByBoard(board);
            boardRepositoryService.delete(board);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return DeleteBoardResponseDto.success();
    }
    // 게시물 삭제
}

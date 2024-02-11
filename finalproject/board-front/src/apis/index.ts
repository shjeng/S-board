import axios from "axios";
import { SignInRequestDto, SignUpRequestDto } from "./request/auth";
import { SignInResponseDto, SignUpResponseDto } from "./response/auth";
import { ResponseDto } from "./response";
import { GetSignInUserResponseDto, GetUserResponseDto, PatchNicknameResponseDto, PatchProfileImageResponseDto } from "./response/user";
import { PatchBoardRequestDto, PostBoardRequestDto, PostCommentRequestDto } from "./request/board";
import { DeleteBoardResponseDto, GetBoardResponseDto, GetCommentListResponseDto, GetFavoriteListResponseDto, GetLatestBoardListResponseDto, GetSearchBoardListResponseDto, GetTop3BoardListResponseDto, GetUserBoardListResponseDto, IncreaseViewCountResponseDto, PatchBoardResponseDto, PostBoardResponseDto, PostCommentResponseDto, PutFavoriteResponseDto } from "./response/board";
import { GetPopularListResponseDto, GetRelationListResponseDto } from "./response/search";
import { PatchNicknameRequestDto, PatchProfileImageRequestDto } from "./request/user";

const DOMAIN = 'http://localhost:8081';
const API_DOMAIN = `${DOMAIN}/api/v1`;
const authorization = (accessToken: string) => {
  return { headers: { Authorization: `Bearer ${accessToken}`} }
}; 

const SIGN_UP_URL = () => `${API_DOMAIN}/auth/sign-up`;

// 게시물 상세보기, 
const GET_BOARD_URL = (boardId : string | BigInt) => `${API_DOMAIN}/board/${boardId}`;
const INCREASE_VIEW_COUNT_URL = (boardId: string | BigInt) => `${API_DOMAIN}/board/${boardId}/increase-view-count`;
export const getBoardRequest = async (boardId: string|BigInt) =>{
  const result = await axios.get(GET_BOARD_URL(boardId))
      .then(response =>{
        const responseBody: GetBoardResponseDto = response.data;
        return responseBody;
      })
      .catch(error => {
        if(!error.response) return null;
        const responseBody: ResponseDto = error.response.data;
        return responseBody;
      })
  return result;
}

// 최신 게시물 
const GET_LATEST_BOARD_LIST_URL = () => `${API_DOMAIN}/board/latest-list`;
export const getLatestBoardListRequest = async() => {
  const result = await axios.get(GET_LATEST_BOARD_LIST_URL())
      .then(response => {
        const responseBody: GetLatestBoardListResponseDto = response.data;
        return responseBody;
      })
      .catch(error => {
        if(!error.response) return null;
        const responseBody:ResponseDto = error.response.data;
        return responseBody;
      })
  return result;
}
// 탑3 게시물
const GET_TOP3_BOARD_LIST_URL = () => `${API_DOMAIN}/board/top-3`;
export const getTop3tBoardListRequest = async() => {
  const result = await axios.get(GET_TOP3_BOARD_LIST_URL())
      .then(response => {
        const responseBody: GetTop3BoardListResponseDto = response.data;
        return responseBody;
      })
      .catch(error => {
        if(!error.response) return null;
        const responseBody:ResponseDto = error.response.data;
        return responseBody;
      })
  return result;
}
// 검색어로 게시물 가져오기 
const GET_SEARCH_BOARD_LIST_URL = (searchWord: string, preSearchWord: string | null ) => `${API_DOMAIN}/board/search-list/${searchWord}${preSearchWord ? '/' + preSearchWord : ''}`;
export const getSearchBoardListRequest = async (searchWord: string, preSearchWord: string | null) => {
  const result = await axios.get(GET_SEARCH_BOARD_LIST_URL(searchWord,preSearchWord))
      .then(response => {
        const responseBody: GetSearchBoardListResponseDto = response.data;
        return responseBody;
      })
      .catch(error => {
        if(!error.response) return null;
        const responseBody: ResponseDto = error.response.data;
        return responseBody;
      })
  return result;
}

// 유저 게시물 가져오기 
const GET_USER_BOARD_LIST_URL = (email:string) => `${API_DOMAIN}/board/user-board-list/${email}`;
export const getUserBoardListRequest = async (email : string) => {
  const reuslt = await axios.get(GET_USER_BOARD_LIST_URL(email))
      .then(response => {
        const responseBody : GetUserBoardListResponseDto = response.data;
        return responseBody;
      })
      .catch(error => {
        if(!error.response) return null;
        const responseBody: ResponseDto = error.response.data;
        return responseBody;
      })
  return reuslt;
}

// 인기 검색어 
const GET_POPULAR_LIST_URL = () => `${API_DOMAIN}/search/popular-list`;
export const getPopularListRequest = async () => {
  const result = await axios.get(GET_POPULAR_LIST_URL())
      .then(response =>{
          const responseBody: GetPopularListResponseDto  = response.data;
          return responseBody;
      })
      .catch(error => {
        if(!error.response) return null;
        const responseBody:ResponseDto = error.response.data;
        return responseBody;
      })
  return result;
}

// 연관 검색어 
const GET_RELATION_LIST_URL = (searchWord: string) => `${API_DOMAIN}/search/${searchWord}/relation-list`;
export const getRelationlistRequest = async (searchWord: string) => {
  const result = await axios.get(GET_RELATION_LIST_URL(searchWord)) 
      .then(response=>{
        const responseBody:GetRelationListResponseDto = response.data;
        return responseBody;
      })
      .catch(error=>{
        if(!error.response) return null;
        const responseBody:ResponseDto = error.response.data;
        return responseBody;
      })
  return result;
}
// 게시물 작성
const POST_BOARD_URL = () => `${API_DOMAIN}/board`;
export const postBoardRequest = async (requestBody: PostBoardRequestDto, accessToken: string) => {
  const result = await axios.post(POST_BOARD_URL(), requestBody,authorization(accessToken))
      .then(response => {
        const responseBody: PostBoardResponseDto = response.data;
        return responseBody;
      })
      .catch(error => {
        if(!error.response) return null;
        const responseBody: ResponseDto = error.response.data;
        return responseBody;
      })
  return result;
}

//  회원 정보 가져오기 
const GET_USER_URL = (email:string) => `${API_DOMAIN}/user/${email}`;
export const getUserRequest = async(email: string) => {
  const result = await axios.get(GET_USER_URL(email))
      .then(response=>{
        const responseBody:GetUserResponseDto = response.data;
        return responseBody;
      })
      .catch(error=>{
        if(!error.response) return null;
        const responseBody : ResponseDto = error.response.data;
        return responseBody;
      })
  return result;
}

// 로그인 요청
const SIGN_IN_URL = () => `${API_DOMAIN}/auth/sign-in`;
export const signInRequest = async (requestBody: SignInRequestDto) =>{  
  // await : 응답이 올 때까지 기다리겠다., requestBody: 어떤 데이터를 넣을 것인지
  const result = await axios.post(SIGN_IN_URL(),requestBody)
            .then(response => {
              const responseBody: SignInResponseDto = response.data;
              return responseBody;
            })
            .catch(error=>{
              if(!error.response.data) {
                return null;
              }
              const responseBody: ResponseDto = error.response.data;
              return responseBody;
            })
  return result;
}

// 닉네임 수정
const PATCH_NICKNAME_URL = () => `${API_DOMAIN}/user/nickname`;
export const patchNicknameRequest = async(requestBody: PatchNicknameRequestDto, accessToken: string) => {
  const result = await axios.patch(PATCH_NICKNAME_URL(),requestBody,authorization(accessToken))
      .then(response => {
        const responseBody: PatchNicknameResponseDto = response.data;
        return responseBody;
      })
      .catch(error => {
        if(!error.response) return null;
        const responseBody: ResponseDto = error.response.data;
        return responseBody;
      })
  return result;
}

// 프로필 이미지 수정
const PATCH_PROFILE_IMAGE_URL = () => `${API_DOMAIN}/user/profile`;
export const patchProfileImageRequest = async(requestBody: PatchProfileImageRequestDto, accessToken: string) => {
  const result = await axios.patch(PATCH_PROFILE_IMAGE_URL(),requestBody,authorization(accessToken))
      .then(response => {
        const responseBody: PatchProfileImageResponseDto = response.data;
        return responseBody;
      })
      .catch(error => {
        if(!error.response) return null;
        const responseBody: ResponseDto = error.response.data;
        return responseBody;
      })
  return result;
}
// 회원가입 요청
export const signUpRequest = async (requestBody: SignUpRequestDto) =>{
  const result = await axios.post(SIGN_UP_URL(), requestBody)
      .then(response => {
        const responseBody: SignUpResponseDto = response.data;
        return responseBody;
      })
      .catch(error =>{
        if(!error.response.data) return null;
        const responseBody: ResponseDto = error.response.data;
        return responseBody;
      })
      return result;
}


//조회수 증가 
export const increaseViewCountRequest = async (boardId: BigInt | string) => {
  const result = await axios.get(INCREASE_VIEW_COUNT_URL(boardId))
      .then(response =>{
        const responseBody: IncreaseViewCountResponseDto = response.data;
        return responseBody;
      })
      .catch(error => {
        if(!error.response) return null;
        const responseBody: ResponseDto = error.response.data;
        return responseBody;
      });
    return result;
}
// 게시물 좋아요 가져오기 
const GET_FAVORITE_LIST_URL = (boardId: BigInt | string) => `${API_DOMAIN}/board/${boardId}/favorite-list`;
export const getFavoriteListRequest  = async (boardId: BigInt | string) =>{
  const result = await axios.get(GET_FAVORITE_LIST_URL(boardId))
      .then(response => {
        const responseBody: GetFavoriteListResponseDto = response.data;
        return responseBody;
      })
      .catch(error =>{
        if(error.response) return null;
        const responseBody: ResponseDto = error.response.data;
        return responseBody;
      })
  return result;
}
// 게시물 댓글 가져오기 
const GET_COMMENT_LIST_URL = (boardId: BigInt | string) => `${API_DOMAIN}/board/${boardId}/comment-list`;
export const getCommentListRequest  = async (boardId: BigInt | string) =>{
  const result = await axios.get(GET_COMMENT_LIST_URL(boardId))
      .then(response => {
        const responseBody: GetCommentListResponseDto = response.data;
        return responseBody;
      })
      .catch(error =>{
        if(error.response) return null;
        const responseBody: ResponseDto = error.response.data;
        return responseBody;
      })
  return result;
}

// 게시글 수정 
const PATCH_BOARD_URL = (boardId: BigInt | string) => `${API_DOMAIN}/board/${boardId}`;
export const patchBoardRequest = async(boardId: BigInt | string, requestBody: PatchBoardRequestDto, accessToken: string) => {
  const result = await axios.patch(PATCH_BOARD_URL(boardId),requestBody, authorization(accessToken))
        .then(response => {
          const responseBody: PatchBoardResponseDto = response.data;
          return responseBody;
        })
        .catch(error => {
          if(!error.response) return null;
          const responseBody: ResponseDto = error.response.data;
          return responseBody;
        })
    return result;
  
}
// 좋아요 버튼 클릭
const PUT_FAVORITE_URL = (boardId : string | BigInt) => `${API_DOMAIN}/board/${boardId}/favorite`
export const putFavoriteRequest = async (boardId : string | BigInt, accessToken : string) => {
  const result = await axios.put(PUT_FAVORITE_URL(boardId),{}, authorization(accessToken))
      .then(response=>{
        const responseBody : PutFavoriteResponseDto = response.data;
        return responseBody;
      })
      .catch(error=>{
        if(!error.response) return null;
        const responseBody: ResponseDto = error.response.data;
        return responseBody;
      })
  return result;
}
// 댓글 작성하기
const POST_COMMENT_URL =(boardId: string | BigInt) => `${API_DOMAIN}/board/${boardId}/comment`;
export const postCommentRequest = async (boardId: BigInt | string, requestBody: PostCommentRequestDto, accessToken: string) =>{
  const result = await axios.post(POST_COMMENT_URL(boardId),requestBody,authorization(accessToken))
    .then(response =>{
      const responseBody:PostCommentResponseDto = response.data;
      return responseBody;
    })
    .catch(error => {
      if(!error.response) return null;
      const responseBody: ResponseDto = error.response.data;
      return responseBody;
    })
  return result;
}
// 게시물 삭제하기 
const DELETE_BOARD_URL = (boardId: string | BigInt) =>  `${API_DOMAIN}/board/${boardId}`;
export const deleteBoardRequest =async (boardId:string | BigInt, accessToken: string) => {
  const result = await axios.delete(DELETE_BOARD_URL(boardId),authorization(accessToken))
      .then(response=>{
        const responseBody: DeleteBoardResponseDto = response.data;
        return responseBody;
      })
      .catch(error => {
        if(!error.response) return null;
        const responseBody:ResponseDto = error.response.data;
        return responseBody;
      })
  return result;
  
}

// App.tsx에서 cookie.accessToken 값이 바뀌먼 실행하는 Request 함수 
const GET_SIGN_IN_USER_URL = () => `${API_DOMAIN}/user`;
export const getSignInUserRequest = async (accessToken: string) => {
  const result = await axios.get(GET_SIGN_IN_USER_URL(), authorization(accessToken))
      .then(response => {
        const responseBody: GetSignInUserResponseDto = response.data;
        return responseBody;
      }).catch(error=>{
        if(!error.response) return null;
        const responseBody: ResponseDto = error.response.data;
        return responseBody;
      })
  return result;
}

const FILE_DOMAIN = `${DOMAIN}/file`;
const FILE_UPLOAD_URL =() =>`${FILE_DOMAIN}/upload`;
const mutipartFormData = {headers:{'Content-Type':'multipart/form-data'}}; // 요청 설정
export const fileUploadRequest = async (data: FormData) => {
  const result = await axios.post(FILE_UPLOAD_URL(),data,mutipartFormData)
      .then(response=>{
        const responseBody: string = response.data;
        return responseBody; // 백엔드에서 url만 반환해줌
      })
      .catch(error => {
        return null;
      })
  return result;
}
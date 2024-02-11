export default interface BoardItem{
  boardNumber: bigint;
  title: string;
  content: string;
  boardTitleImage: string | null;
  favoriteCount: number;
  commentCount: number;
  viewCount: bigint;
  writeDatetime: string;
  writerNickname: string;
  writerProfileImage: string | null;
}
export default interface Board{
  boardNumber: bigint;
  title: string;
  content: string;
  boardImageList: string[];
  writeDatetime: string;
  writerEmail: string;
  writerNickname: string;
  writerProfileImage: string | null;
}

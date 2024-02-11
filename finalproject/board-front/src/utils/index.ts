export const convertUrlToFile  = async (url: string) => {
  const response = await fetch(url); // 사진 url 브라우저에 입력하면 데이터를 볼 수 있음. 그거에 대한 response를 말하는듯? 
  const data = await response.blob();
  // await response.blob(): response 객체의 blob 메서드는 응답 데이터를 Blob 객체로 변환합니다. Blob은 바이너리 데이터를 나타내는 객체로, 이미지, 오디오, 비디오 등과 같은 멀티미디어 데이터를 처리하는 데 사용됩니다.
  const extend = url.split('.').pop();
  const fileName = url.split('/').pop();
  const meta = {type: `image/${extend}`};
  return new File([data], fileName as string, meta);
  //return new File([data], fileName as string, meta);: File 클래스를 사용하여 Blob 데이터를 기반으로 새로운 File 객체를 생성합니다. File 생성자는 배열 형태의 Blob 데이터, 파일 이름, 메타데이터를 인자로 받습니다.
}

export const convertUrlsToFile = async(urls: string[]) =>{
  const files: File[] = [];
  for(const url of urls) {
    const file = await convertUrlToFile(url);
    files.push(file);
  }
  return files;
}
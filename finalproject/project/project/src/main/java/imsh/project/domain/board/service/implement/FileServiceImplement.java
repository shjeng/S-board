package imsh.project.domain.board.service.implement;

import imsh.project.domain.board.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Objects;
import java.util.UUID;

@Service
public class FileServiceImplement implements FileService {

    @Value("${file.path}")
    private String filePath;
    @Value("${file.url}")
    private String fileUrl;
    @Override
    public String upload(MultipartFile file) {
        if(file.isEmpty()) return null;
        String originalFileName = file.getOriginalFilename();
        String extension = Objects.requireNonNull(originalFileName).substring(originalFileName.lastIndexOf(".")); // 확장자
        String uuid = UUID.randomUUID().toString(); // 랜덤 이름으로
        String saveFileName = uuid + extension;
        String savePath = filePath + saveFileName;
        try{
            file.transferTo(new File(savePath));
        } catch (Exception exception){
            exception.printStackTrace();
            return null;
        }
        String url = fileUrl + saveFileName;
        return url;
    }

    @Override
    public Resource getImage(String filename) {
        Resource resource = null;
        try{
            resource = new UrlResource("file:" + filePath + filename);
        }catch (Exception exception){
            exception.printStackTrace();
            return null;
        }
        return resource;
    }
}

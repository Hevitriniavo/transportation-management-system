package hei.shool.transportationmanagementsystem.services;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    String saveFile(MultipartFile file,  String folderName);

    boolean deleteImage(String url);

    String createDirectoryIfNotExist(String directory);
}

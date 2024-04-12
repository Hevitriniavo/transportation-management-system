package hei.shool.transportationmanagementsystem.services.impl;

import hei.shool.transportationmanagementsystem.exceptions.InternalServerException;
import hei.shool.transportationmanagementsystem.services.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Value("${upload.dir}")
    private String uploadDir;

    @Override
    public String saveFile(MultipartFile file, String folderName) {
        try {
            String fileName = generateUniqueFileName(Objects.requireNonNull(file.getOriginalFilename()));
            Path uploadPath = getUploadPath(folderName);
            createDirectoryIfNotExist(uploadPath);
            Path filePath = uploadPath.resolve(fileName);
            file.transferTo(filePath.toFile());
            return buildFileUrl(fileName, folderName);
        } catch (IOException e) {
            throw new InternalServerException("Une erreur s'est produite lors de l'enregistrement du fichier.");
        }
    }

    @Override
    public boolean deleteImage(String url) {
        try {
            String fileName = extractFileNameFromUrl(url);
            Path filePath = getUploadPath().resolve(fileName);
            if (Files.exists(filePath)) {
                Files.delete(filePath);
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            throw new InternalServerException("Une erreur s'est produite lors de la suppression du fichier : " + e.getMessage());
        }
    }

    @Override
    public String createDirectoryIfNotExist(String directory) {
        try {
            Path directoryPath = getUploadPath(directory);
            createDirectoryIfNotExist(directoryPath);
            return directoryPath.toString();
        } catch (IOException e) {
            throw new InternalServerException("Une erreur s'est produite lors de la création du répertoire : " + e.getMessage());
        }
    }

    private Path getUploadPath() throws IOException {
        return Paths.get(ResourceUtils.getFile("classpath:static").getAbsolutePath(), uploadDir);
    }

    private Path getUploadPath(String directory) throws IOException {
        return Paths.get(ResourceUtils.getFile("classpath:static").getAbsolutePath(), uploadDir, directory);
    }


    private void createDirectoryIfNotExist(Path directoryPath) throws IOException {
        if (!Files.exists(directoryPath)) {
            Files.createDirectories(directoryPath);
        }
    }

    private String generateUniqueFileName(String originalFilename) {
        String uuid = UUID.randomUUID().toString();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        return uuid + extension;
    }

    private String buildFileUrl(String fileName, String folderName) {
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(uploadDir + "/")
                .path(folderName + "/")
                .path(fileName)
                .toUriString();
    }

    private String extractFileNameFromUrl(String url) {
        int index = url.indexOf("uploads/");
        if (index != -1) {
            return url.substring(index + "uploads/".length());
        } else {
            throw new IllegalArgumentException("L'URL fournie ne contient pas la référence 'uploads/'.");
        }
    }
}

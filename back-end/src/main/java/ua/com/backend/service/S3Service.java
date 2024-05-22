package ua.com.backend.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface S3Service {

    String save(MultipartFile file) throws IOException;

    String getURL(String s3Key);

    byte[] getFileByURL(String fileURL) throws IOException;

}
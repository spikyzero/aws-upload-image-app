package ua.com.backend.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileService {

    void save(MultipartFile file) throws IOException;

    List<byte[]> readAllByLabel(String labelName) throws IOException;

}
package ua.com.backend.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.com.backend.service.FileService;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/api/v1/files")
@CrossOrigin(origins = "*")
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/save")
    public void uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return;
        }
        fileService.save(file);
    }

    @GetMapping("/read/all")
    public ResponseEntity<List<String>> readFiles(@RequestParam("tag") String labelName) throws IOException {
        List<byte[]> images = fileService.readAllByLabel(labelName);
        List<String> base64Images = new ArrayList<>();
        for (byte[] array : images) {
            String base64Image = Base64.getEncoder().encodeToString(array);
            base64Images.add(base64Image);
        }
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(base64Images);
    }

}
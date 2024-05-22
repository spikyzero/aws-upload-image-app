package ua.com.backend.service;

import java.io.IOException;
import java.util.List;
import com.amazonaws.services.rekognition.model.Label;
import org.springframework.web.multipart.MultipartFile;

public interface RekognitionService {

    List<Label> getLabels(MultipartFile file) throws IOException;

}
package ua.com.backend.service.impl;

import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.model.DetectLabelsRequest;
import com.amazonaws.services.rekognition.model.DetectLabelsResult;
import com.amazonaws.services.rekognition.model.Image;
import com.amazonaws.services.rekognition.model.Label;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ua.com.backend.service.RekognitionService;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;

@Service
public class DefaultRekognitionService implements RekognitionService {

    private final AmazonRekognition rekognitionClient;

    public DefaultRekognitionService(AmazonRekognition rekognitionClient) {
        this.rekognitionClient = rekognitionClient;
    }

    @Override
    public List<Label> getLabels(MultipartFile file) throws IOException {
        ByteBuffer imageBytes = ByteBuffer.wrap(file.getBytes());
        DetectLabelsRequest request = new DetectLabelsRequest()
                .withImage(new Image().withBytes(imageBytes))
                .withMaxLabels(5);
        DetectLabelsResult result = rekognitionClient.detectLabels(request);
        return result.getLabels();
    }

}
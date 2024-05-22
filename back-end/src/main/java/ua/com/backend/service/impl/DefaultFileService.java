package ua.com.backend.service.impl;

import com.amazonaws.services.rekognition.model.Label;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ua.com.backend.model.ImageMetaData;
import ua.com.backend.model.LabelMetaData;
import ua.com.backend.service.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class DefaultFileService implements FileService {

    private final S3Service s3Service;
    private final RekognitionService rekognitionService;
    private final ImageMetaDataService imageMetaDataService;
    private final LabelMetaDataService labelMetaDataService;
    private final ImageLabelConfidenceService imageLabelConfidenceService;

    public DefaultFileService(
            S3Service s3Service,
            RekognitionService rekognitionService,
            ImageMetaDataService imageMetaDataService,
            LabelMetaDataService labelMetaDataService,
            ImageLabelConfidenceService imageLabelConfidenceService) {
        this.s3Service = s3Service;
        this.rekognitionService = rekognitionService;
        this.imageMetaDataService = imageMetaDataService;
        this.labelMetaDataService = labelMetaDataService;
        this.imageLabelConfidenceService = imageLabelConfidenceService;
    }

    @Override
    public void save(MultipartFile file) throws IOException {
        String s3Key = s3Service.save(file);
        String imageURL = s3Service.getURL(s3Key);
        List<Label> rekognitionLabels = rekognitionService.getLabels(file);
        if (imageMetaDataService.save(imageURL)) {
            List<LabelMetaData> labels = labelMetaDataService.saveAll(rekognitionLabels);
            imageLabelConfidenceService.saveAll(labels, imageURL);
        }
    }

    @Override
    public List<byte[]> readAllByLabel(String labelName) throws IOException {
        List<ImageMetaData> images = imageMetaDataService.readAll(labelName);
        if (images.isEmpty()) {
            return Collections.emptyList();
        }
        List<byte[]> imagesBytes = new ArrayList<>();
        for (ImageMetaData image : images) {
            byte[] file = s3Service.getFileByURL(image.getImageURL());
            imagesBytes.add(file);
        }
        return imagesBytes;
    }

}
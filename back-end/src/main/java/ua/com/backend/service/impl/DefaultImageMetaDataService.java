package ua.com.backend.service.impl;

import org.springframework.stereotype.Service;
import ua.com.backend.model.ImageLabelConfidence;
import ua.com.backend.model.ImageMetaData;
import ua.com.backend.model.LabelMetaData;
import ua.com.backend.repository.ImageLabelConfidenceRepository;
import ua.com.backend.repository.ImageMetaDataRepository;
import ua.com.backend.service.ImageMetaDataService;
import ua.com.backend.service.LabelMetaDataService;

import java.util.Collections;
import java.util.List;

@Service
public class DefaultImageMetaDataService implements ImageMetaDataService {

    private final ImageMetaDataRepository imageMetaDataRepository;
    private final LabelMetaDataService labelMetaDataService;
    private final ImageLabelConfidenceRepository imageLabelConfidenceRepository;

    public DefaultImageMetaDataService(
            ImageMetaDataRepository imageMetaDataRepository,
            LabelMetaDataService labelMetaDataService,
            ImageLabelConfidenceRepository imageLabelConfidenceRepository) {
        this.imageMetaDataRepository = imageMetaDataRepository;
        this.labelMetaDataService = labelMetaDataService;
        this.imageLabelConfidenceRepository = imageLabelConfidenceRepository;
    }

    public boolean save(String imageURL) {
        if (imageURL == null || imageURL.isEmpty() || imageMetaDataRepository.existsByImageURL(imageURL)) {
            System.out.println("The same imageURL already exists");
            return false;
        }
        ImageMetaData imageMetaData = new ImageMetaData();
        imageMetaData.setImageURL(imageURL);
        imageMetaDataRepository.save(imageMetaData);
        return true;
    }

    @Override
    public ImageMetaData read(String imageURL) {
        return imageMetaDataRepository.findByImageURL(imageURL);
    }

    @Override
    public List<ImageMetaData> readAll(String labelName) {
        LabelMetaData label = labelMetaDataService.read(labelName);
        if (label == null) {
            return Collections.emptyList();
        }
        List<ImageLabelConfidence> imageLabelConfidences = imageLabelConfidenceRepository.findAllByLabel_Id(label.getId());
        return imageLabelConfidences.stream().map(ImageLabelConfidence::getImage).toList();
    }

}
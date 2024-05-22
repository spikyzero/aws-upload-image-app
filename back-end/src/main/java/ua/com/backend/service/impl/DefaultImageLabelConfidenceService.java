package ua.com.backend.service.impl;

import org.springframework.stereotype.Service;
import ua.com.backend.model.ImageLabelConfidence;
import ua.com.backend.model.ImageMetaData;
import ua.com.backend.model.LabelMetaData;
import ua.com.backend.repository.ImageLabelConfidenceRepository;
import ua.com.backend.service.ImageLabelConfidenceService;
import ua.com.backend.service.ImageMetaDataService;

import java.util.List;

@Service
public class DefaultImageLabelConfidenceService implements ImageLabelConfidenceService {

    private final ImageLabelConfidenceRepository imageLabelConfidenceRepository;
    private final ImageMetaDataService imageMetaDataService;

    public DefaultImageLabelConfidenceService(ImageLabelConfidenceRepository imageLabelConfidenceRepository, ImageMetaDataService imageMetaDataService) {
        this.imageLabelConfidenceRepository = imageLabelConfidenceRepository;
        this.imageMetaDataService = imageMetaDataService;
    }

    @Override
    public void saveAll(List<LabelMetaData> labels, String imageURL) {
        ImageMetaData image = imageMetaDataService.read(imageURL);
        for (LabelMetaData label : labels) {
            ImageLabelConfidence imageLabelConfidence = new ImageLabelConfidence();
            imageLabelConfidence.setLabel(label);
            imageLabelConfidence.setImage(image);
            imageLabelConfidenceRepository.save(imageLabelConfidence);
        }
    }

    public List<ImageLabelConfidence> readAllByLabelId(Long labelId) {
        return imageLabelConfidenceRepository.findAllByLabel_Id(labelId);
    }

}
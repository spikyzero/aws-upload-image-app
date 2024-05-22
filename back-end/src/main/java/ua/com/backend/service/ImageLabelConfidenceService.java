package ua.com.backend.service;

import ua.com.backend.model.ImageLabelConfidence;
import ua.com.backend.model.LabelMetaData;

import java.util.List;

public interface ImageLabelConfidenceService {

    void saveAll(List<LabelMetaData> labels, String imageURL);

    List<ImageLabelConfidence> readAllByLabelId(Long labelId);

}
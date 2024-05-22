package ua.com.backend.service;

import com.amazonaws.services.rekognition.model.Label;
import ua.com.backend.model.LabelMetaData;

import java.util.List;

public interface LabelMetaDataService {

    List<LabelMetaData> saveAll(List<Label> rekognitionLabels);

    LabelMetaData read(String labelName);

}
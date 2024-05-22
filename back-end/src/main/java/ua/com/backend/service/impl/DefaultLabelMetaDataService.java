package ua.com.backend.service.impl;

import com.amazonaws.services.rekognition.model.Label;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ua.com.backend.model.LabelMetaData;
import ua.com.backend.repository.LabelMetaDataRepository;
import ua.com.backend.service.LabelMetaDataService;

import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultLabelMetaDataService implements LabelMetaDataService {

    private final LabelMetaDataRepository labelMetaDataRepository;

    public DefaultLabelMetaDataService(LabelMetaDataRepository labelMetaDataRepository) {
        this.labelMetaDataRepository = labelMetaDataRepository;
    }

    @Override
    public List<LabelMetaData> saveAll(List<Label> rekognitionLabels) {
        return saveOnlyUnique(rekognitionLabels);
    }

    @Override
    public LabelMetaData read(String labelName) {
        if (StringUtils.isEmpty(labelName)) {
            return null;
        }
        return labelMetaDataRepository.readByName(labelName);
    }

    private List<LabelMetaData> saveOnlyUnique(List<Label> rekognitionLabels) {
        List<LabelMetaData> labels = new ArrayList<>();
        for (Label rekognitionLabel : rekognitionLabels) {
            LabelMetaData label = labelMetaDataRepository.readByName(rekognitionLabel.getName());
            if (label != null) {
                labels.add(label);
                System.out.println("Label with name '" + rekognitionLabel.getName() + "' already exists");
                continue;
            }
            label = new LabelMetaData();
            label.setName(rekognitionLabel.getName());
            LabelMetaData newLabel = labelMetaDataRepository.save(label);
            labels.add(newLabel);
        }
        return labels;
    }

}
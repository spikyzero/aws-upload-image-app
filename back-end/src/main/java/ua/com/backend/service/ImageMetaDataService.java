package ua.com.backend.service;


import ua.com.backend.model.ImageMetaData;

import java.util.List;

public interface ImageMetaDataService {

    boolean save(String imageURL);

    ImageMetaData read(String imageURL);

    List<ImageMetaData> readAll(String labelName);

}
package ua.com.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.backend.model.ImageMetaData;

public interface ImageMetaDataRepository extends JpaRepository<ImageMetaData, Long> {

    boolean existsByImageURL(String imageURL);

    ImageMetaData findByImageURL(String imageURL);

}
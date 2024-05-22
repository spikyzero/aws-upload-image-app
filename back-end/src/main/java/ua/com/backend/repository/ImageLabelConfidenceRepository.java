package ua.com.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.backend.model.ImageLabelConfidence;

import java.util.List;

public interface ImageLabelConfidenceRepository extends JpaRepository<ImageLabelConfidence, Long> {

    List<ImageLabelConfidence> findAllByLabel_Id(long labelId);

}
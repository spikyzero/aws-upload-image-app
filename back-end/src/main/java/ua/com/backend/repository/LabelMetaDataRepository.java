package ua.com.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.backend.model.LabelMetaData;

public interface LabelMetaDataRepository extends JpaRepository<LabelMetaData, Long> {

    LabelMetaData readByName(String name);

}
package ua.com.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class LabelMetaData extends BaseEntity {

    @Column(unique = true, nullable = false)
    String name;

    @OneToMany(mappedBy = "label")
    List<ImageLabelConfidence> imageLabelConfidences;

}
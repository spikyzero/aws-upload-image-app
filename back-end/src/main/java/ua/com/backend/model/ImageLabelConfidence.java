package ua.com.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class ImageLabelConfidence extends BaseEntity {

    @ManyToOne
    ImageMetaData image;

    @ManyToOne
    LabelMetaData label;

    double confidence;

}

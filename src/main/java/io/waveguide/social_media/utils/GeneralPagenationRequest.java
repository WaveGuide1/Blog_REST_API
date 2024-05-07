package io.waveguide.social_media.utils;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;

@Data
@NoArgsConstructor
public class GeneralPagenationRequest {

    private Integer pageNumber;
    private Integer pageSize;
    private String sortBy;
    private String value;
}

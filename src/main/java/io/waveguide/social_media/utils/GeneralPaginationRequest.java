package io.waveguide.social_media.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeneralPaginationRequest {

    private Integer pageNumber;
    private Integer pageSize;

}

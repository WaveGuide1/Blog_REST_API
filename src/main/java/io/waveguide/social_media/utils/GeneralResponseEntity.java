package io.waveguide.social_media.utils;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GeneralResponseEntity<T> {

    T info;
    private String message;
}

package io.waveguide.social_media.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ChangePasswordRequest {

    @NotBlank(message = "This field can not be empty")
    private String currentPassword;
    @NotBlank(message = "This field can not be empty")
    private String newPassword;
    @NotBlank(message = "This field can not be empty")
    private String confirmationPassword;
}

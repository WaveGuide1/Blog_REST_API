package io.waveguide.social_media.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginUserRequest {

    @NotBlank(message = "Name is required")
    private String fullName;

    @NotBlank(message = "Username is required")
    private String username;

    @Email(message = "Provide a valid email")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    private Integer role;
}

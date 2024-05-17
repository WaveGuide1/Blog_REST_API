package io.waveguide.social_media.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginUserRequest {

    @NotBlank(message = "User name is required parameter.")
    private String username;

    @NotBlank(message = "Password is required parameter.")
    private String password;

    private int isSocialRegister;

    @Override
    public String toString() {
        return "LoginUserRequest{" +
                "userName='" + username + '\'' +
                ", isSocialRegister=" + isSocialRegister +
                '}';
    }
}

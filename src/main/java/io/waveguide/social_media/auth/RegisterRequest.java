package io.waveguide.social_media.auth;

import io.waveguide.social_media.user.Role;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

  @NotBlank(message = "First name is required")
  private String firstname;
  @NotBlank(message = "Last name is required")
  private String lastname;
  @NotBlank(message = "Email is required")
  private String email;
  @NotBlank(message = "Password is required")
  private String password;
  private Role role;
}

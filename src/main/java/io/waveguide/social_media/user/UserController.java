package io.waveguide.social_media.user;

import io.waveguide.social_media.exception.AuthenticationFailedException;
import io.waveguide.social_media.exception.GeneralAppException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    // Change password controller
    @PatchMapping("/change/password")
    public ResponseEntity<?> changePassword(
          @Valid @RequestBody ChangePasswordRequest request,
          Principal connectedUser
    ) {
        try {
            service.changePassword(request, connectedUser);
            return ResponseEntity.ok().build();
        } catch (AuthenticationFailedException ex){
            throw ex;
        } catch (Exception e){
            throw new GeneralAppException("Something is not right");
        }

    }
}

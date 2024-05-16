package io.waveguide.social_media.user;

import io.waveguide.social_media.exception.RecordNotFoundException;
import io.waveguide.social_media.exception.UserAlreadyExistException;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User registerUser(RegisterUserRequest userRequest){

        User saveUser = userRepository.findByEmail(userRequest.getEmail());
        if(!Objects.isNull(saveUser))
            throw new UserAlreadyExistException("Email is already in use");
        User user = new User();
        BeanUtils.copyProperties(userRequest, user);
        user.setCreateAt(LocalDateTime.now());
        return userRepository.save(user);
    }

    public User login(LoginUserRequest userRequest) throws AuthenticationException {
        User saveUser = userRepository.findByEmail(userRequest.getEmail());
        if(Objects.isNull(saveUser))
            throw new RecordNotFoundException("You need to register first..");
        User user = userRepository.findByUsernameAndPassword(userRequest.getUsername(), userRequest.getPassword());
        if(Objects.isNull(user))
            throw new AuthenticationException("Invalid credentials");
        return user;
    }
}

package io.waveguide.social_media.user;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    User findByUsername(String username);

    User findByUserIdAndOtp(String userId, Integer otp);
}

package io.waveguide.social_media.post;

import io.waveguide.social_media.user.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PostRepository extends MongoRepository<Post, String> {

    Post deleteByPostId(String postId);

    Post findByPostId(String postId);
}

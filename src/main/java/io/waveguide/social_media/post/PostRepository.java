package io.waveguide.social_media.post;

import io.waveguide.social_media.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.awt.print.Pageable;
import java.util.List;

public interface PostRepository extends MongoRepository<Post, String> {

    Post deleteByPostId(String postId);

    List<Post> findByUsername(String postIs, Pageable pageable);

    User findByPostId(String postId);
}

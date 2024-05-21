package io.waveguide.social_media.post;

import io.waveguide.social_media.comment.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface PostRepository extends MongoRepository<Post, String> {

    void deleteByPostId(String postId);

    Post findByPostId(String postId);

    @Query("{'postId' : ?0, 'isDeleted': false}")
    Page<Post> findByPostIdAndIsDeletedFalse(Pageable pageable);

    @Query("{'postId' : ?0, 'isDeleted': false}")
    Post findByPostIdAndIsDeletedFalse(String postId);
}

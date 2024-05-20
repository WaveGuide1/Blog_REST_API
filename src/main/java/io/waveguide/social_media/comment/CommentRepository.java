package io.waveguide.social_media.comment;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface CommentRepository extends MongoRepository<Comment, String> {


    @Query("{'postId' : ?0, 'isDeleted': false}")
    List<Comment> findByPostIdAndIsDeletedFalse(String postId, Pageable pageable);

    @Query("{'commentId' : ?0, 'isDeleted': false}")
    Optional<Comment> findByCommentIdAndIsDeletedFalse(String commentId);
}

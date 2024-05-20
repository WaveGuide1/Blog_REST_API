package io.waveguide.social_media.comment;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface CommentRepository extends MongoRepository<Comment, String> {

    Comment deleteByCommentId(String commentId);

    @Query("{'comment.postId' : ?0, 'isDeleted': true}")
    List<Comment> findByPostIdAndIsDeletedFalse(String postId, Pageable pageable);

    @Query("{'comment.commentId' : ?0, 'isDeleted': true}")
    Optional<Comment> findByCommentIdAndIsDeletedFalse(String commentId);
}

package io.waveguide.social_media.comment;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.awt.print.Pageable;
import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String> {

    Comment deleteByCommentId(String commentId);

    List<Comment> findByPostId(String commentId, Pageable pageable);
}

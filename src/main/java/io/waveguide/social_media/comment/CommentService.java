package io.waveguide.social_media.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public Comment createComment(Comment comment) throws Exception{
        return commentRepository.save(comment);
    }

    public Comment updateComment(Comment comment) throws Exception{
        return commentRepository.save(comment);
    }

    public List<Comment> getComments(String postId, Pageable pageable) throws Exception{
        return commentRepository.findByPostId(postId, pageable);
    }

    public Comment deleteComment(String commentId) throws Exception{
        return commentRepository.deleteByCommentId(commentId);
    }

}

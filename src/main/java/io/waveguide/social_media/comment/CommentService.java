package io.waveguide.social_media.comment;

import io.waveguide.social_media.exception.AuthenticationFailedException;
import io.waveguide.social_media.exception.RecordNotFoundException;
import io.waveguide.social_media.post.PostRepository;
import io.waveguide.social_media.user.User;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    // Add comment
    public Comment createComment(CreateCommentRequest request, String postId, Principal principal) throws Exception{
        var user = (User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        var post = postRepository.findByPostId(postId);
        if(post == null) throw new RecordNotFoundException("The post is not available");
        Comment comment = new Comment();
        BeanUtils.copyProperties(request, comment);
        comment.setUserId(user.getId());
        comment.setPostId(post.getPostId());
        comment.setCreateAt(LocalDateTime.now());
        Comment saveComment = commentRepository.save(comment);
        post.getComments().add(saveComment);
        postRepository.save(post);
        return saveComment;
    }

    // Update comment
    public Comment updateComment(UpdateCommentRequest request, String commentId, Principal principal) throws Exception{
        var user = (User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        var oldComment = commentRepository.findByCommentIdAndIsDeletedFalse(commentId)
                .orElseThrow(() -> new RecordNotFoundException("Comment not found"));
        if(!oldComment.getUserId().equals(user.getId()))
            throw new AuthenticationFailedException("Invalid Request");
        Comment comment = new Comment();
        BeanUtils.copyProperties(request, comment);
        comment.setCommentId(oldComment.getCommentId());
        comment.setUserId(oldComment.getUserId());
        comment.setPostId(oldComment.getPostId());
        comment.setCreateAt(oldComment.getCreateAt());
        comment.setUpdatedAt(LocalDateTime.now());
        return commentRepository.save(comment);
    }

    // Delete a comment (False delete)
    public void deleteComment(String commentId, Principal principal) throws Exception{
        var user = (User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        var oldComment = commentRepository.findByCommentIdAndIsDeletedFalse(commentId)
                .orElseThrow(() -> new RecordNotFoundException("Comment not found"));
        if(!oldComment.getUserId().equals(user.getId()))
            throw new AuthenticationFailedException("Invalid Request");
        var post = postRepository.findByPostId(oldComment.getPostId());
        if(ObjectUtils.isEmpty(post)) throw new RecordNotFoundException("Post not found");
        oldComment.setDeleted(true);
        commentRepository.save(oldComment);
        post.getComments().removeIf(comment -> comment.getCommentId().equals(commentId));
        postRepository.save(post);
    }

}

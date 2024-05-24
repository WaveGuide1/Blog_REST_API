package io.waveguide.social_media.comment;

import io.waveguide.social_media.exception.AuthenticationFailedException;
import io.waveguide.social_media.exception.GeneralAppException;
import io.waveguide.social_media.exception.RecordNotFoundException;
import io.waveguide.social_media.utils.GeneralResponseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/comments")
public class CommentController {

    private final CommentService commentService;

    // Comment on a post
    @PostMapping("/{postId}")
    public ResponseEntity<GeneralResponseEntity<Comment>> addComment(@RequestBody CreateCommentRequest request,
                                                                     @PathVariable String postId, Principal principal) {
        GeneralResponseEntity<Comment> responseEntity = new GeneralResponseEntity<>();
        try {
            Comment comment = commentService.createComment(request, postId, principal);
            responseEntity.setMessage("You commented on this post");
            responseEntity.setInfo(comment);
            return ResponseEntity.ok(responseEntity);
        } catch (RecordNotFoundException e){
            throw e;
        } catch (Exception e){
            throw new GeneralAppException("Server not responding. Try again later");
        }
    }

    // Update a comment
    @PatchMapping("/{commentId}")
    public ResponseEntity<GeneralResponseEntity<Comment>>updateComment(@RequestBody UpdateCommentRequest request,
                                                                       @PathVariable String commentId, Principal principal){
        GeneralResponseEntity<Comment> responseEntity = new GeneralResponseEntity<>();
        try {
            Comment comment = commentService.updateComment(request, commentId, principal);
            responseEntity.setMessage("Comment updated successfully");
            responseEntity.setInfo(comment);
            return ResponseEntity.ok(responseEntity);
        } catch (RecordNotFoundException | AuthenticationFailedException e){
            throw e;
        } catch (Exception e){
            throw new GeneralAppException("Server not responding. Try again later");
        }
    }

    // Delete a comment (False delete)
    @DeleteMapping("/{commentId}")
    public ResponseEntity<GeneralResponseEntity<Comment>> deleteComment(@PathVariable String commentId, Principal principal){
        GeneralResponseEntity<Comment> responseEntity = new GeneralResponseEntity<>();
        try {
            commentService.deleteComment(commentId, principal);
            responseEntity.setMessage("Comment deleted successfully");
            return ResponseEntity.ok(responseEntity);
        }catch (RecordNotFoundException | AuthenticationFailedException e){
            throw e;
        } catch (Exception e){
            throw new GeneralAppException("Server not responding. Try again later");
        }
    }
}

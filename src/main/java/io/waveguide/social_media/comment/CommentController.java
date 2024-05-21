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

    @PostMapping("/{postId}")
    public ResponseEntity<GeneralResponseEntity<Comment>> addComment(CreateCommentRequest request, String postId, Principal principal) throws Exception {
        GeneralResponseEntity<Comment> responseEntity = new GeneralResponseEntity<>();
        try {
            Comment comment = commentService.createComment(request, postId, principal);
            responseEntity.setMessage("You commented");
            responseEntity.setInfo(comment);
            return ResponseEntity.ok(responseEntity);
        } catch (Exception e){
            throw e;
        }
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity<GeneralResponseEntity<Comment>>updateComment(UpdateCommentRequest request, String commentId, Principal principal){
        GeneralResponseEntity<Comment> responseEntity = new GeneralResponseEntity<>();
        try {
            Comment comment = commentService.updateComment(request, commentId, principal);
            responseEntity.setMessage("Comment updated successfully");
            responseEntity.setInfo(comment);
            return ResponseEntity.ok(responseEntity);
        } catch (RecordNotFoundException | AuthenticationFailedException e){
            throw e;
        } catch (Exception e){
            throw new GeneralAppException("Something went wrong");
        }
    }

    @DeleteMapping("/{commentId}")
    public void deleteComment(String commentId, Principal principal){
        try {
            commentService.deleteComment(commentId, principal);
        }catch (RecordNotFoundException | AuthenticationFailedException e){
            throw e;
        } catch (Exception e){
            throw new GeneralAppException("Something went wrong");
        }
    }
}

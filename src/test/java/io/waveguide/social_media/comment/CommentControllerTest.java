package io.waveguide.social_media.comment;

import io.waveguide.social_media.utils.GeneralResponseEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.security.Principal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CommentControllerTest {

    @Mock
    private CommentService commentService;

    @InjectMocks
    private CommentController commentController;

    @Test
    void addCommentTest() throws Exception {
        CreateCommentRequest request = new CreateCommentRequest();

        Principal principal = mock(Principal.class);

        String postId = "110";

        when(commentService.createComment(any(CreateCommentRequest.class), eq(postId), any(Principal.class))).thenReturn(new Comment());

        ResponseEntity<GeneralResponseEntity<Comment>> responseEntity = commentController.addComment(request, postId, principal);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        verify(commentService, times(1)).createComment(any(CreateCommentRequest.class), eq(postId), any(Principal.class));
    }

    @Test
    void updateCommentTest() throws Exception {

        UpdateCommentRequest request = new UpdateCommentRequest();

        String commentId = "110";

        Principal principal = mock(Principal.class);

        when(commentService.updateComment(any(UpdateCommentRequest.class), eq(commentId), any(Principal.class))).thenReturn(new Comment());

        ResponseEntity<GeneralResponseEntity<Comment>> responseEntity = commentController.updateComment(request, commentId, principal);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        verify(commentService, times(1)).updateComment(any(UpdateCommentRequest.class), eq(commentId), any(Principal.class));
    }

    @Test
    void deleteCommentTest() throws Exception {

        Principal principal = mock(Principal.class);

        String commentId = "110";

        ResponseEntity<GeneralResponseEntity<Comment>> responseEntity = commentController.deleteComment(commentId, principal);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        verify(commentService, times(1)).deleteComment(eq(commentId), any(Principal.class));
    }
}

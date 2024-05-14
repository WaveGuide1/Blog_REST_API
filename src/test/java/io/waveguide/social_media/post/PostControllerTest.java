package io.waveguide.social_media.post;


import io.waveguide.social_media.exception.RecordNotFoundException;
import io.waveguide.social_media.utils.GeneralPaginationRequest;
import io.waveguide.social_media.utils.GeneralResponseEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks; import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Collections; import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class PostControllerTest {

    @Mock
    private PostService postService;

    @InjectMocks
    private PostController postController;

    @Test
    void testGetPosts() throws Exception {

        GeneralPaginationRequest paginationRequest = new GeneralPaginationRequest();

        when(postService.getPosts(any())).thenReturn(Collections.emptyList());

        ResponseEntity<GeneralResponseEntity<Post>> responseEntity = postController.getPosts(0, 0, "id", "userId");

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        verify(postService, times(1)).getPosts(any());

    }

    @Test
    void testCreatePost() throws Exception {

        CreatePostRequest request = new CreatePostRequest();

        when(postService.createPost(any())).thenReturn(new Post());

        ResponseEntity<GeneralResponseEntity<Post>> responseEntity = postController.createPost(request);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        verify(postService, times(1)).createPost(any());

    }

    @Test
    void testUpdatePost() throws Exception {

        UpdatePostRequest request = new UpdatePostRequest();

        when(postService.updatePost(any())).thenReturn(new Post());

        ResponseEntity<GeneralResponseEntity<Post>> responseEntity = postController.updatePost(request);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        verify(postService, times(1)).updatePost(any());

    }

    @Test
    void testGetPostBy() throws Exception {

        String postId = "postId";

        when(postService.getPost(any())).thenReturn(new Post());

        ResponseEntity<GeneralResponseEntity<Post>> responseEntity = postController.getPost(postId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        verify(postService, times(1)).getPost(any());

    }

    @Test
    void testDeletePost() throws Exception {

        String postId = "postId";

        ResponseEntity<GeneralResponseEntity<Post>> responseEntity = postController.deletePost(postId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        verify(postService, times(1)).deletePost(any());

    }

}
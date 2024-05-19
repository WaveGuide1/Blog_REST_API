package io.waveguide.social_media.post;


import io.waveguide.social_media.exception.RecordNotFoundException;
import io.waveguide.social_media.utils.GeneralPaginationRequest;
import io.waveguide.social_media.utils.GeneralResponseEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks; import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.security.Principal;
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


        List<Post> postList = Collections.singletonList(new Post());
        Page<Post> postPage = new PageImpl<>(postList, PageRequest.of(0, 10), 1);
        when(postService.getPosts(anyInt(), anyInt(), anyString())).thenReturn(postPage);
        ResponseEntity<Page<Post>> responseEntity = postController.getPosts(0, 10, "id");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(postPage, responseEntity.getBody());
        verify(postService, times(1)).getPosts(anyInt(), anyInt(), anyString());

    }

    @Test
    void testCreatePost() throws Exception {

        CreatePostRequest request = new CreatePostRequest();

        Principal principal = mock(Principal.class);

        when(postService.createPost(any(CreatePostRequest.class), any(Principal.class))).thenReturn(new Post());

        ResponseEntity<GeneralResponseEntity<Post>> responseEntity = postController.createPost(request, principal);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        verify(postService, times(1)).createPost(any(CreatePostRequest.class), any(Principal.class));

    }

    @Test
    void testUpdatePost() throws Exception {

        UpdatePostRequest request = new UpdatePostRequest();

        Principal principal = mock(Principal.class);

        String postId = "101";

        when(postService.updatePost(any(UpdatePostRequest.class),eq(postId), any(Principal.class))).thenReturn(new Post());

        ResponseEntity<GeneralResponseEntity<Post>> responseEntity = postController.updatePost(request, postId, principal);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        verify(postService, times(1)).updatePost(any(UpdatePostRequest.class), eq(postId), any(Principal.class

        ));

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
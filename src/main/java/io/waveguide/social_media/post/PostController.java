package io.waveguide.social_media.post;

import io.waveguide.social_media.exception.AuthenticationFailedException;
import io.waveguide.social_media.exception.RecordNotFoundException;
import io.waveguide.social_media.utils.GeneralPaginationRequest;
import io.waveguide.social_media.utils.GeneralResponseEntity;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@Validated
@RequestMapping("/api/v1/posts")
public class PostController {

    private final PostService postService;

    @GetMapping("/")
    public ResponseEntity<Page<Post>> getPosts(@Valid
                                                                @RequestParam(defaultValue = "0") Integer pageNo,
                                                                @RequestParam(defaultValue = "10") Integer pageSize,
                                                                @RequestParam(defaultValue = "id") String sortBy) throws Exception {
        try {
            Page<Post> posts = postService.getPosts(pageNo, pageSize, sortBy);
            return ResponseEntity.ok(posts);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    public ResponseEntity<GeneralResponseEntity<Post>> createPost(@Valid
                                                                  @RequestBody CreatePostRequest request,
                                                                  Principal principal) throws Exception {
        GeneralResponseEntity<Post> generalResponseEntity = new GeneralResponseEntity<>();

            Post createdPost = postService.createPost(request, principal);
            generalResponseEntity.setMessage("Post created successfully");
            generalResponseEntity.setInfo(createdPost);
            return ResponseEntity.ok(generalResponseEntity);


    }

    @PutMapping("/{postId}")
    public ResponseEntity<GeneralResponseEntity<Post>> updatePost(@Valid
                                                                  @RequestBody UpdatePostRequest request,
                                                                  String postId, Principal principal) {

        GeneralResponseEntity<Post> generalResponseEntity = new GeneralResponseEntity<>();
        try {
            Post updatedPost = postService.updatePost(request, postId, principal);
            if (ObjectUtils.isEmpty(updatedPost)) throw new RecordNotFoundException("Not Found");

            generalResponseEntity.setMessage("Post updated successfully");
            generalResponseEntity.setInfo(updatedPost);
            return ResponseEntity.ok(generalResponseEntity);

        } catch (RecordNotFoundException | AuthenticationFailedException ex) {
            throw ex;

        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    @GetMapping("/{postId}")
    public ResponseEntity<GeneralResponseEntity<Post>> getPost(@Valid
                                                                  @PathVariable String postId) {
        Post post = new Post();
        GeneralResponseEntity<Post> generalResponseEntity = new GeneralResponseEntity<>();
        try {
            Post savedPost = postService.getPost(postId);
            generalResponseEntity.setInfo(savedPost);
            return ResponseEntity.ok(generalResponseEntity);
        } catch (RecordNotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<GeneralResponseEntity<Post>> deletePost(@Valid
                                                                  @PathVariable String postId) {
        Post post = new Post();
        GeneralResponseEntity<Post> generalResponseEntity = new GeneralResponseEntity<>();
        try {
            postService.deletePost(postId);
            generalResponseEntity.setMessage("Post deleted successfully");
            return ResponseEntity.ok(generalResponseEntity);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}

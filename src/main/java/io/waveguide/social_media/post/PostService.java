package io.waveguide.social_media.post;

import io.waveguide.social_media.comment.CommentService;
import io.waveguide.social_media.exception.AuthenticationFailedException;
import io.waveguide.social_media.exception.RecordNotFoundException;
import io.waveguide.social_media.user.User;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final CommentService commentService;

    public Post createPost(CreatePostRequest request, Principal principal) throws Exception{
        var user = (User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        Post post = new Post();
        BeanUtils.copyProperties(request, post);
        post.setCreateAt(LocalDateTime.now());
        post.setUserId(user.getId());
        return postRepository.save(post);
    }

    public Post updatePost(UpdatePostRequest request, String postId, Principal principal) throws Exception{
        var user = (User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();

        Post post = postRepository.findByPostIdAndIsDeletedFalse(postId);
        if(ObjectUtils.isEmpty(post)) throw new RecordNotFoundException("Not Found");
        if(!post.getUserId().equals(user.getId())) throw new AuthenticationFailedException("Invalid Request");
        BeanUtils.copyProperties(request, post);
        post.setUpdatedAt(LocalDateTime.now());
        return postRepository.save(post);
    }

    public void deletePost(String postId) throws Exception {
        Post post = postRepository.findByPostId(postId);
        if(ObjectUtils.isEmpty(post)) throw new RecordNotFoundException("Not found");
        post.setDeleted(true);
        postRepository.save(post);
    }

    public Post getPost(String postId) throws Exception {
        Post savedPost = postRepository.findByPostIdAndIsDeletedFalse(postId);
        if(savedPost == null)
            throw new RecordNotFoundException("No Record Found");
        return savedPost;
    }

    public Page<Post> getPosts(int pageNo, int pageSize, String sortBy) throws Exception{
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());
        return postRepository.findByPostIdAndIsDeletedFalse(pageable);
    }
}

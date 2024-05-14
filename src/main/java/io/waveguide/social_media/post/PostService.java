package io.waveguide.social_media.post;

import io.waveguide.social_media.exception.RecordNotFoundException;
import io.waveguide.social_media.utils.GeneralPaginationRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Post createPost(CreatePostRequest request) throws Exception{
        Post post = new Post();
        BeanUtils.copyProperties(request, post);
        post.setCreateAt(LocalDateTime.now());
        return postRepository.save(post);
    }

    public Post updatePost(UpdatePostRequest request) throws Exception{
        Post post = postRepository.findByPostId(request.getPostId());
        if(ObjectUtils.isEmpty(post)) return null;
        BeanUtils.copyProperties(request, post);
        post.setUpdatedAt(LocalDateTime.now());
        return postRepository.save(post);
    }

    public void deletePost(String postId) throws Exception {
        postRepository.deleteByPostId(postId);
    }

    public Post getPost(String postId) throws Exception {
        Post savedPost = postRepository.findByPostId(postId);
        if(savedPost == null)
            throw new RecordNotFoundException("No Record Found");
        return savedPost;
    }

    public Page<Post> getPosts(int pageNo, int pageSize, String sortBy) throws Exception{
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());
        return postRepository.findAll(pageable);
    }
}

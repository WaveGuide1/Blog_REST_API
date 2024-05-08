package io.waveguide.social_media.post;

import io.waveguide.social_media.utils.GeneralPaginationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Post createPost(Post post) throws Exception{
        return postRepository.save(post);
    }

    public Post updatePost(Post post) throws Exception{
        return postRepository.save(post);
    }

    public void deletePost(String postId) throws Exception {
        postRepository.deleteByPostId(postId);
    }

    public Post getPost(String postId) throws Exception {
        return postRepository.findByPostId(postId);
    }

    public List<Post> getPosts(GeneralPaginationRequest paginationRequest) throws Exception{
        Pageable pageable = (Pageable) PageRequest.of(paginationRequest.getPageNumber(),
                paginationRequest.getPageSize(), Sort.by(paginationRequest.getSortBy())
                        .descending());
        return postRepository.findByUserId(paginationRequest.getValue(), pageable);
    }
}

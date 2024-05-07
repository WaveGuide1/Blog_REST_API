package io.waveguide.social_media.post;

import lombok.RequiredArgsConstructor;
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

    public Post deletePost(String postId) throws Exception {
        return postRepository.deleteByPostId(postId);
    }

    public Post getPost(String postId) throws Exception {
        return postRepository.findByPostId(postId);
    }

    public List<Post> getPosts(String userId, Pageable pageable) throws Exception{
        return postRepository.findByUserId(userId, pageable);
    }
}

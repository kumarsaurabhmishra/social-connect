package com.saurabh.social_connect.service.Impl;

import com.saurabh.social_connect.entity.Post;
import com.saurabh.social_connect.entity.User;
import com.saurabh.social_connect.repository.PostRepository;
import com.saurabh.social_connect.repository.UserRepository;
import com.saurabh.social_connect.service.PostService;
import com.saurabh.social_connect.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserService userService;
    private final UserRepository userRepository;

    public PostServiceImpl(PostRepository postRepository, UserService userService, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @Override
    public Post createPost(Post postRequest, Long userId) throws Exception {

        User user = userService.getUserByUserId(userId);
        Post post = new Post();

        post.setCaption(postRequest.getCaption());
        post.setImage(postRequest.getImage());
        post.setVideo(postRequest.getVideo());
        post.setUser(user);
        post.setCreatedAt(LocalDateTime.now());

        return postRepository.save(post);
    }

    @Override
    public String deletePost(Long postId, Long userId) throws Exception {

        Post post = findPostById(postId);
        Long postUserId = post.getUser().getUserId();

        if(userId.equals(postUserId)) {
            postRepository.deleteById(postId);
            return "Post Deleted";
        } else {
            throw new Exception("Can not delete post");
        }

    }

    @Override
    public List<Post> findPostsByUserId(Long userId) throws Exception {

        User user = userService.getUserByUserId(userId);

        return postRepository.findAllByUser(user);

    }

    @Override
    public Post findPostById(Long postId) throws Exception {

        Optional<Post> postOptional = postRepository.findById(postId);

        if(postOptional.isPresent()) {
            return postOptional.get();
        } else {
            throw new Exception("Post not present with Post id: "+postId);
        }


    }

    @Override
    public List<Post> findAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post savedPost(Long postId, Long userId) throws Exception {

        Post post = findPostById(postId);
        User user = userService.getUserByUserId(userId);

        if(user.getSavedPosts().contains(post)) {
            user.getSavedPosts().remove(post);
        } else
            user.getSavedPosts().add(post);

        userRepository.save(user);

        return post;

    }

    @Override
    public Post likePost(Long postId, Long userId) throws Exception {
        Post post = findPostById(postId);
        User user = userService.getUserByUserId(userId);

        if(post.getLiked().contains(user)) {
            post.getLiked().remove(user);
        } else
            post.getLiked().add(user);

        return postRepository.save(post);
    }
}

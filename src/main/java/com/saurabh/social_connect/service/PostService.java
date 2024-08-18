package com.saurabh.social_connect.service;

import com.saurabh.social_connect.entity.Post;

import java.util.List;

public interface PostService {

    Post createPost(Post post, Long userId) throws Exception;

    String deletePost(Long postId, Long userId) throws  Exception;

    List<Post> findPostsByUserId(Long userId) throws Exception;

    Post findPostById(Long postId) throws Exception;

    List<Post> findAllPosts();

    Post savedPost(Long postId, Long userId) throws Exception;

    Post likePost(Long postId, Long userId) throws Exception;


}

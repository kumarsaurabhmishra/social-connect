package com.saurabh.social_connect.service.Impl;

import com.saurabh.social_connect.entity.Comment;
import com.saurabh.social_connect.entity.Post;
import com.saurabh.social_connect.entity.User;
import com.saurabh.social_connect.repository.CommentRepository;
import com.saurabh.social_connect.repository.PostRepository;
import com.saurabh.social_connect.service.CommentService;
import com.saurabh.social_connect.service.PostService;
import com.saurabh.social_connect.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final PostService postService;
    private final UserService userService;
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Override
    public Comment createComment(Comment comment, Long postId, Long userId) throws Exception {

        User user = userService.getUserByUserId(userId);
        Post post = postService.findPostById(postId);

        comment.setUser(user);
        comment.setContent(comment.getContent());
        comment.setCreatedAt(LocalDateTime.now());

        Comment comment1 = commentRepository.save(comment);
        post.getComments().add(comment);
        postRepository.save(post);

        return comment1;
    }

    @Override
    public Comment likeComment(Long commentId, Long userId) {
        Comment comment = findCommentById(commentId);
        User user = userService.getUserByUserId(userId);

        if(!comment.getLike().contains(user)) {
            comment.getLike().add(user);
        } else {
            comment.getLike().remove(user);
        }

        return commentRepository.save(comment);
    }

    @Override
    public Comment findCommentById(Long commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }
}

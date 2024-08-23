package com.saurabh.social_connect.service;

import com.saurabh.social_connect.entity.Comment;
import com.saurabh.social_connect.entity.Post;
import com.saurabh.social_connect.entity.User;
import org.hibernate.annotations.Comments;

public interface CommentService {

    public Comment createComment(Comment comment, Long postId, Long userId) throws Exception;

    public Comment likeComment(Long commentId, Long userId);

    public Comment findCommentById(Long commentId);

}

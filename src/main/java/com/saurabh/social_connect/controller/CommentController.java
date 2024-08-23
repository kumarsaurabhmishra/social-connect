package com.saurabh.social_connect.controller;

import com.saurabh.social_connect.entity.Comment;
import com.saurabh.social_connect.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/create")
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment, @RequestParam("postId") Long postId, @RequestParam("userId") Long userId) throws Exception {
        return ResponseEntity.ok(commentService.createComment(comment, postId, userId));
    }

    @PutMapping("/like")
    public ResponseEntity<Comment> likeComment(@RequestParam("commentId") Long commentId, @RequestParam("userId") Long userId) {
        return ResponseEntity.ok(commentService.likeComment(commentId, userId));
    }

    @GetMapping("/comment")
    public ResponseEntity<Comment> findCommentById(@RequestParam("commentId") Long commentId) {
        return ResponseEntity.ok(commentService.findCommentById(commentId));
    }


}

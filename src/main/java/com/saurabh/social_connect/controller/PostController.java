package com.saurabh.social_connect.controller;

import com.saurabh.social_connect.Response.ApiResponse;
import com.saurabh.social_connect.entity.Post;
import com.saurabh.social_connect.service.PostService;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/posts")
@AllArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/create")
    public ResponseEntity<Post> createPost(@RequestBody Post postRequest, @RequestParam("userId") Long userId) throws Exception {
        Post post = postService.createPost(postRequest, userId);
        return new ResponseEntity<>(post, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ApiResponse> deletePost(@RequestParam("postId") Long postId, @RequestParam("userId") Long userId) throws Exception {

        String message = postService.deletePost(postId, userId);
        ApiResponse res = new ApiResponse(message, HttpStatus.OK);
        return new ResponseEntity<>(res, HttpStatus.OK);

    }

    @GetMapping("/post")
    public ResponseEntity<Post> findPostById(@RequestParam("postId") Long postId) throws Exception {

        Post post = postService.findPostById(postId);
        return new ResponseEntity<>(post, HttpStatus.OK);

    }

    @GetMapping("/userPosts")
    public ResponseEntity<List<Post>> findUsersAllPost(@RequestParam("userId") Long userId) throws Exception {
        List<Post> posts = postService.findPostsByUserId(userId);

        return ResponseEntity.ok(posts);
    }

    @GetMapping
    public ResponseEntity<List<Post>> findAllPosts() {
        List<Post> posts = postService.findAllPosts();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @PutMapping("/save")
    public ResponseEntity<Post> savePost(@RequestParam("postId") Long postId, @RequestParam("userId") Long userId) throws Exception {
        Post post = postService.savedPost(postId, userId);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @PutMapping("/like")
    public ResponseEntity<Post> likePost(@RequestParam("postId") Long postId, @RequestParam("userId") Long userId) throws Exception {
        Post post = postService.likePost(postId, userId);

        return new ResponseEntity<>(post, HttpStatus.OK);
    }


}

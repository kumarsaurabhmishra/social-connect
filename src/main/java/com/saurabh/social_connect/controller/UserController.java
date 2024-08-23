package com.saurabh.social_connect.controller;

import com.saurabh.social_connect.entity.User;
import com.saurabh.social_connect.service.UserService;
import lombok.AllArgsConstructor;
import org.antlr.v4.runtime.misc.Pair;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping
    public ResponseEntity<List<User>> getAllUser() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserByUserId(@PathVariable("userId") Long userId) {

        User user = userService.getUserByUserId(userId);

        if(user!=null) {
            return new ResponseEntity<>(user,HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping("/{userId}")
    public ResponseEntity<Pair<String, User>> deleteUser(@PathVariable("userId") Long userId) {
        User user = userService.deleteUser(userId);
        if(user!=null) {
            return new ResponseEntity<>(new Pair<>("User Deleted", user), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable("userId") Long userId, @RequestBody User user) {

        User updatedUser = userService.uppdateUser(userId, user);

        if(updatedUser!=null) {
            return ResponseEntity.ok(updatedUser);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @GetMapping("/email")
    public ResponseEntity<User> getUserByEmail(@RequestParam("email") String email) {

        User user = userService.getUserByEmail(email);

        if(user!=null) {
            return ResponseEntity.ok(user);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PutMapping("/follow")
    public ResponseEntity<User> followUser(@RequestParam("follower") Long followerUserId, @RequestParam("following") Long followingUserId) {

        User user = userService.followUser(followerUserId, followingUserId);

        if(user!=null)
            return ResponseEntity.ok(user);

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    @PutMapping("/unfollow")
    public ResponseEntity<User> unfollowUser(@RequestParam("follower") Long followerUserId, @RequestParam("following") Long followingUserId) {

        User user = userService.unfollowUser(followerUserId, followingUserId);

        if(user!=null)
            return ResponseEntity.ok(user);

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    @GetMapping("/search")
    public ResponseEntity<List<User>> searchUser(@RequestParam("query") String query) {

        List<User> users = userService.searchUser(query);

        return ResponseEntity.ok(users);

    }
 }

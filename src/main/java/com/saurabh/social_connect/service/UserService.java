package com.saurabh.social_connect.service;

import com.saurabh.social_connect.entity.User;

import java.util.List;

public interface UserService {

    public User createUser(User user);

    List<User> getAllUsers();

    User getUserByUserId(Long id);

    User getUserByEmail(String email);

    User followUser(Long followerUserId, Long followingUserId);

    User uppdateUser(Long id, User user);

    User deleteUser(Long id);

    List<User> searchUser(String query);

    User unfollowUser(Long followerUserId, Long followingUserId);
}

package com.saurabh.social_connect.service.Impl;

import com.saurabh.social_connect.entity.User;
import com.saurabh.social_connect.repository.UserRepository;
import com.saurabh.social_connect.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByUserId(Long id) {

        Optional<User> user = userRepository.findByUserId(id);
        return user.orElse(null);

    }

    @Override
    public User getUserByEmail(String email) {

        Optional<User> userOptional = userRepository.findByEmail(email);
        return userOptional.orElse(null);

    }

    @Override
    public User followUser(Long followerUserId, Long followingUserId) {

        User follower = getUserByUserId(followerUserId);
        User following = getUserByUserId(followingUserId);

        if(follower!=null && following!=null && !follower.equals(following)) {
            following.getFollowers().add(followerUserId);
            follower.getFollowings().add(followingUserId);
            userRepository.save(follower);
        }

        return follower;

    }

    @Override
    public User deleteUser(Long id) {

        Optional<User> userOptional = userRepository.findByUserId(id);

        if(userOptional.isPresent()) {
            userRepository.deleteById(id);
            return userOptional.get();
        }
        return null;
    }

    @Override
    public List<User> searchUser(String query) {
        return userRepository.searchUser(query);
    }

    @Override
    public User unfollowUser(Long followerUserId, Long followingUserId) {

        User follower = getUserByUserId(followerUserId);
        User following = getUserByUserId(followingUserId);

        if(follower!=null && following!=null && !follower.equals(following)) {
            following.getFollowers().remove(followerUserId);
            follower.getFollowings().remove(followingUserId);
            userRepository.save(follower);
        }
        return follower;
    }

    @Override
    public User uppdateUser(Long id, User user) {

        Optional<User> userOptional = userRepository.findByUserId(id);

        if(userOptional.isPresent()) {
            User user1 = userOptional.get();

            if(user.getFirstName()!=null) {
                user1.setFirstName(user.getFirstName());
            }
            if(user.getLastName()!=null) {
                user1.setLastName(user.getLastName());
            }
            if(user.getEmail()!=null) {
                user1.setEmail(user.getEmail());
            }
            if(user.getPassword()!=null) {
                user1.setPassword(user.getPassword());
            }
            return userRepository.save(user1);
        }
        return null;
    }
}


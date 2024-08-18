package com.saurabh.social_connect.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String gender;

    private Set<Long> followers = new HashSet<>();
    private Set<Long> followings = new HashSet<>();

    @ManyToMany
    private Set<Post> savedPosts = new HashSet<>();

    public User() {
    }

    public User(Long userId, String firstName, String lastName, String email, String password, String gender, Set<Long> followers, Set<Long> followings, Set<Post> savedPosts) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.followers = followers;
        this.followings = followings;
        this.savedPosts = savedPosts;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Set<Long> getFollowers() {
        return followers;
    }

    public void setFollowers(Set<Long> followers) {
        this.followers = followers;
    }

    public Set<Long> getFollowings() {
        return followings;
    }

    public void setFollowings(Set<Long> followings) {
        this.followings = followings;
    }

    public Set<Post> getSavedPosts() {
        return savedPosts;
    }

    public void setSavedPosts(Set<Post> savedPosts) {
        this.savedPosts = savedPosts;
    }
}

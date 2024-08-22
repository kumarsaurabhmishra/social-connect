package com.saurabh.social_connect.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.saurabh.social_connect.enums.Role;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@JsonIgnoreProperties(value = {"enabled", "accountNonExpired", "accountNonLocked", "credentialsNonExpired", "authorities", "username"})
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Character gender;

    private Set<Long> followers = new HashSet<>();
    private Set<Long> followings = new HashSet<>();

    @ManyToMany
    private Set<Post> savedPosts = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private Role role;

    public User() {
    }

    public User(Long userId, String firstName, String lastName, String email, String password, Character gender, Set<Long> followers, Set<Long> followings, Set<Post> savedPosts) {
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

    public String getFirstName() {
        return firstName;
    }

    public void setRole(Role role) {
        this.role = role;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    public Set<Long> getFollowers() {
        return followers;
    }

    public Set<Long> getFollowings() {
        return followings;
    }

    public Set<Post> getSavedPosts() {
        return savedPosts;
    }
}

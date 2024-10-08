package com.saurabh.social_connect.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    private String caption;
    private String image;
    private String video;

    @ManyToOne
    private User user;
    private LocalDateTime createdAt;

    @OneToMany
    private Set<User> liked = new HashSet<>();

    @OneToMany
    private List<Comment> comments = new ArrayList<>();

}

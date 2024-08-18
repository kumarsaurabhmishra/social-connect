package com.saurabh.social_connect.repository;

import com.saurabh.social_connect.entity.Post;
import com.saurabh.social_connect.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    public List<Post> findAllByUser(User user);

}

package com.saurabh.social_connect.repository;

import com.saurabh.social_connect.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserId(Long userId);

    Optional<User> findByEmail(String email);

    @Query("select u from User u where u.firstName like %:query% OR u.lastName like %:query% OR u.email like %:query%")
    List<User> searchUser(@Param("query") String query);
}

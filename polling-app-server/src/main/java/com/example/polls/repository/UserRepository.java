package com.example.polls.repository;

import com.example.polls.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;


public interface UserRepository extends JpaRepository<User , Long> {
    Optional<User> findByUsernameOrEmail(String username , String email) ;
    List<User> findByIdIn(List<Long> userIds);

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}

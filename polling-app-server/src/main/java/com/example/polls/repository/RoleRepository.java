package com.example.polls.repository;

import com.example.polls.models.entity.Role;
import com.example.polls.models.entity.RoleName;
import com.example.polls.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role , Long> {
    Optional<Role> findByName(RoleName roleName) ;
}

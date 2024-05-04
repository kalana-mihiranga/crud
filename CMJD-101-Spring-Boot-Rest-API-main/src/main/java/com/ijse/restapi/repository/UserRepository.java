package com.ijse.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ijse.restapi.entity.User;

@Repository

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUsername(String username);
}

package com.len.spring.rest.api.spring_rest_api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.len.spring.rest.api.spring_rest_api.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findFirstByToken(String token);

}

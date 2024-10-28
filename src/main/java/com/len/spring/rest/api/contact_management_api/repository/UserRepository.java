package com.len.spring.rest.api.contact_management_api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.len.spring.rest.api.contact_management_api.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findFirstByToken(String token);

}

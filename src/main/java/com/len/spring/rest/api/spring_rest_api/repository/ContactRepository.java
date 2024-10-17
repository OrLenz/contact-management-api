package com.len.spring.rest.api.spring_rest_api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.len.spring.rest.api.spring_rest_api.entity.Contact;
import com.len.spring.rest.api.spring_rest_api.entity.User;

@Repository
public interface ContactRepository extends JpaRepository<Contact, String> {

    Optional<Contact> findFirstByUserAndId(User user, String id);

}

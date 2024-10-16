package com.len.spring.rest.api.spring_rest_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.len.spring.rest.api.spring_rest_api.entity.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, String> {

}

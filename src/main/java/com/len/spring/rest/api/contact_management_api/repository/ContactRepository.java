package com.len.spring.rest.api.contact_management_api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.len.spring.rest.api.contact_management_api.entity.Contact;
import com.len.spring.rest.api.contact_management_api.entity.User;

@Repository
public interface ContactRepository extends JpaRepository<Contact, String>, JpaSpecificationExecutor<Contact> {

    Optional<Contact> findFirstByUserAndId(User user, String id);

}

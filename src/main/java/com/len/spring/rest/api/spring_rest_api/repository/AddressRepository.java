package com.len.spring.rest.api.spring_rest_api.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.len.spring.rest.api.spring_rest_api.entity.Address;
import com.len.spring.rest.api.spring_rest_api.entity.Contact;

@Repository
public interface AddressRepository extends JpaRepository<Address, String> {

    Optional<Address> findFirstByContactAndId(Contact contact, String id);

    List<Address> findAllByContact(Contact contact);

}

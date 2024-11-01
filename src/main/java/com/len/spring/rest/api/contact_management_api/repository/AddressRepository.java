package com.len.spring.rest.api.contact_management_api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.len.spring.rest.api.contact_management_api.entity.Address;
import com.len.spring.rest.api.contact_management_api.entity.Contact;

@Repository
public interface AddressRepository extends JpaRepository<Address, String> {

    Optional<Address> findFirstByContactAndId(Contact contact, String id);

    List<Address> findAllByContact(Contact contact);

}

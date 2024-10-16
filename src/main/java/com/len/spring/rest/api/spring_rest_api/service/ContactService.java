package com.len.spring.rest.api.spring_rest_api.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.len.spring.rest.api.spring_rest_api.dto.ContactResponse;
import com.len.spring.rest.api.spring_rest_api.dto.CreateContactRequest;
import com.len.spring.rest.api.spring_rest_api.entity.Contact;
import com.len.spring.rest.api.spring_rest_api.entity.User;
import com.len.spring.rest.api.spring_rest_api.repository.ContactRepository;

import jakarta.transaction.Transactional;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ValidationService validationService;

    @Transactional
    public ContactResponse create(User user ,CreateContactRequest request) {
        validationService.validate(request);

        Contact contact = new Contact();
        contact.setId(UUID.randomUUID().toString());
        contact.setFirstName(request.getFirstname());
        contact.setLastName(request.getLastname());
        contact.setEmail(request.getEmail());
        contact.setPhone(request.getPhone());
        contact.setUser(user);

        contactRepository.save(contact);

        return ContactResponse.builder()
            .id(contact.getId())
            .firstname(contact.getFirstName())
            .lastname(contact.getLastName())
            .email(contact.getEmail())
            .phone(contact.getPhone())
            .build();
    }


    

}

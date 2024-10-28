package com.len.spring.rest.api.contact_management_api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContactResponse {

    private String id;

    private String firstname;

    private String lastname;

    private String email;

    private String phone;

}

package com.len.spring.rest.api.spring_rest_api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.len.spring.rest.api.spring_rest_api.dto.AddressResponse;
import com.len.spring.rest.api.spring_rest_api.dto.CreateAddressRequest;
import com.len.spring.rest.api.spring_rest_api.dto.UpdateAddressRequest;
import com.len.spring.rest.api.spring_rest_api.dto.WebResponse;
import com.len.spring.rest.api.spring_rest_api.entity.Address;
import com.len.spring.rest.api.spring_rest_api.entity.Contact;
import com.len.spring.rest.api.spring_rest_api.entity.User;
import com.len.spring.rest.api.spring_rest_api.repository.AddressRepository;
import com.len.spring.rest.api.spring_rest_api.repository.ContactRepository;
import com.len.spring.rest.api.spring_rest_api.repository.UserRepository;
import com.len.spring.rest.api.spring_rest_api.security.BCrypt;

@SpringBootTest
@AutoConfigureMockMvc
public class AddressControllerTest {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        addressRepository.deleteAll();
        contactRepository.deleteAll();
        userRepository.deleteAll();

        User user = new User();
        user.setUsername("test");
        user.setPassword(BCrypt.hashpw("test", BCrypt.gensalt()));
        user.setName("Test");
        user.setToken("test");
        user.setTokenExpiredAt(System.currentTimeMillis() + 10000000000L);
        userRepository.save(user);

        Contact contact = new Contact();
        contact.setId("test");
        contact.setUser(user);
        contact.setFirstName("Eko");
        contact.setLastName("Khannedy");
        contact.setEmail("eko@example.com");
        contact.setPhone("081233567854");
        contactRepository.save(contact);
    }

    @Test
    void createAddressBadRequest() throws Exception {
        CreateAddressRequest createAddressRequest = new CreateAddressRequest();
        createAddressRequest.setCountry("");

        mockMvc.perform(
            post("/api/contacts/test/addresses")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createAddressRequest))
                .header("X-API-TOKEN", "test")
        ).andExpectAll(
            status().isBadRequest()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<String>>() {});

            assertNotNull(response.getErrors());
        });
    }

    @Test
    void createAddressSuccess() throws Exception {
        CreateAddressRequest createAddressRequest = new CreateAddressRequest();
        createAddressRequest.setStreet("Jl. Sabang");
        createAddressRequest.setCity("Jakarta");
        createAddressRequest.setProvince("DKJ");
        createAddressRequest.setCountry("Indonesia");
        createAddressRequest.setPostalCode("11441");

        mockMvc.perform(
            post("/api/contacts/test/addresses")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createAddressRequest))
                .header("X-API-TOKEN", "test")
        ).andExpectAll(
            status().isOk()
        ).andDo(result -> {
            WebResponse<AddressResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<AddressResponse>>() {});

            assertNull(response.getErrors());
            assertEquals(createAddressRequest.getStreet(), response.getData().getStreet());
            assertEquals(createAddressRequest.getCity(), response.getData().getCity());
            assertEquals(createAddressRequest.getProvince(), response.getData().getProvince());
            assertEquals(createAddressRequest.getCountry(), response.getData().getCountry());
            assertEquals(createAddressRequest.getPostalCode(), response.getData().getPostalCode());

            assertTrue(addressRepository.existsById(response.getData().getId()));
        });
    }

    @Test
    void getAddressNotFound() throws Exception {
        mockMvc.perform(
            get("/api/contacts/test/addresses/test")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .header("X-API-TOKEN", "test")
        ).andExpectAll(
            status().isNotFound()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<String>>() {});

            assertNotNull(response.getErrors());
        });
    }

    @Test
    void getAddressSuccess() throws Exception {
        Contact contact = contactRepository.findById("test").orElseThrow();

        Address address = new Address();
        address.setContact(contact);
        address.setId("test");
        address.setStreet("jalan");
        address.setCity("city");
        address.setProvince("province");
        address.setCountry("country");
        address.setPostalCode("11111");
        addressRepository.save(address);

        mockMvc.perform(
            get("/api/contacts/test/addresses/test")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .header("X-API-TOKEN", "test")
        ).andExpectAll(
            status().isOk()
        ).andDo(result -> {
            WebResponse<AddressResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<AddressResponse>>() {});

            assertNull(response.getErrors());
            assertEquals(address.getId(), response.getData().getId());
            assertEquals(address.getStreet(), response.getData().getStreet());
            assertEquals(address.getCity(), response.getData().getCity());
            assertEquals(address.getProvince(), response.getData().getProvince());
            assertEquals(address.getCountry(), response.getData().getCountry());
            assertEquals(address.getPostalCode(), response.getData().getPostalCode());
        });
    }

    @Test
    void updateAddressBadRequest() throws Exception {
        UpdateAddressRequest UpdateAddressRequest = new UpdateAddressRequest();
        UpdateAddressRequest.setCountry("");

        mockMvc.perform(
            put("/api/contacts/test/addresses/test")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(UpdateAddressRequest))
                .header("X-API-TOKEN", "test")
        ).andExpectAll(
            status().isBadRequest()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<String>>() {});

            assertNotNull(response.getErrors());
        });
    }

    @Test
    void updateAddressSuccess() throws Exception {
        Contact contact = contactRepository.findById("test").orElseThrow();

        Address address = new Address();
        address.setContact(contact);
        address.setId("test");
        address.setStreet("lama");
        address.setCity("lama");
        address.setProvince("lama");
        address.setCountry("lama");
        address.setPostalCode("11111");
        addressRepository.save(address);

        UpdateAddressRequest UpdateAddressRequest = new UpdateAddressRequest();
        UpdateAddressRequest.setStreet("Jl. Sabang");
        UpdateAddressRequest.setCity("Jakarta");
        UpdateAddressRequest.setProvince("DKJ");
        UpdateAddressRequest.setCountry("Indonesia");
        UpdateAddressRequest.setPostalCode("11441");

        mockMvc.perform(
            put("/api/contacts/test/addresses/test")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(UpdateAddressRequest))
                .header("X-API-TOKEN", "test")
        ).andExpectAll(
            status().isOk()
        ).andDo(result -> {
            WebResponse<AddressResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<AddressResponse>>() {});

            assertNull(response.getErrors());
            assertEquals(UpdateAddressRequest.getStreet(), response.getData().getStreet());
            assertEquals(UpdateAddressRequest.getCity(), response.getData().getCity());
            assertEquals(UpdateAddressRequest.getProvince(), response.getData().getProvince());
            assertEquals(UpdateAddressRequest.getCountry(), response.getData().getCountry());
            assertEquals(UpdateAddressRequest.getPostalCode(), response.getData().getPostalCode());

            assertTrue(addressRepository.existsById(response.getData().getId()));
        });
    }

    @Test
    void deleteAddressNotFound() throws Exception {
        mockMvc.perform(
            delete("/api/contacts/test/addresses/test")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .header("X-API-TOKEN", "test")
        ).andExpectAll(
            status().isNotFound()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<String>>() {});

            assertNotNull(response.getErrors());
        });
    }

    @Test
    void deleteAddressSuccess() throws Exception {
        Contact contact = contactRepository.findById("test").orElseThrow();

        Address address = new Address();
        address.setContact(contact);
        address.setId("test");
        address.setStreet("jalan");
        address.setCity("city");
        address.setProvince("province");
        address.setCountry("country");
        address.setPostalCode("11111");
        addressRepository.save(address);

        mockMvc.perform(
            delete("/api/contacts/test/addresses/test")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .header("X-API-TOKEN", "test")
        ).andExpectAll(
            status().isOk()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<String>>() {});

            assertNull(response.getErrors());
            assertEquals("OK", response.getData());

            assertFalse(addressRepository.existsById("test"));
        });
    }

    @Test
    void listAddressNotFound() throws Exception {
        mockMvc.perform(
            get("/api/contacts/salah/addresses")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .header("X-API-TOKEN", "test")
        ).andExpectAll(
            status().isNotFound()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<String>>() {});

            assertNotNull(response.getErrors());
        });
    }

    @Test
    void listAddressSuccess() throws Exception {
        Contact contact = contactRepository.findById("test").orElseThrow();

        for (int i = 0; i < 5; i++) {
            Address address = new Address();
            address.setContact(contact);
            address.setId("test-" + i);
            address.setStreet("jalan");
            address.setCity("city");
            address.setProvince("province");
            address.setCountry("country");
            address.setPostalCode("11111");
            addressRepository.save(address);
        }

        mockMvc.perform(
            get("/api/contacts/test/addresses")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .header("X-API-TOKEN", "test")
        ).andExpectAll(
            status().isOk()
        ).andDo(result -> {
            WebResponse<List<AddressResponse>> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<List<AddressResponse>>>() {});

            assertNull(response.getErrors());
            assertEquals(5, response.getData().size());
        });
    }

}

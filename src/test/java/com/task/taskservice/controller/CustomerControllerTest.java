package com.task.taskservice.controller;

import com.task.taskservice.entity.Customer;
import com.task.taskservice.repository.CustomerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
class CustomerControllerTest {

    private final String SERVICE_URL = "http://localhost:8080/api/v1/customers";

    private final MockMvc mockMvc;

    private final CustomerRepository customerRepository;

    private Customer testCustomer;


    @Autowired
    public CustomerControllerTest(MockMvc mockMvc, CustomerRepository customerRepository) {
        this.mockMvc = mockMvc;
        this.customerRepository = customerRepository;
    }

    @BeforeEach
    void setUp() {
        testCustomer = customerRepository.save(new Customer("TestTitle"));
    }

    @AfterEach
    void clean() {
        customerRepository.delete(testCustomer);
    }

    @Test
    void shouldGetAllCustomers_whenFindAll_thenStatus200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(SERVICE_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(("[{\"title\":\"TestTitle\"}]")));
    }

    @Test
    void shouldGetCustomerById_whenFindBuId_thenStatus200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(SERVICE_URL + "/{customerId}", testCustomer.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(("{\"title\":\"TestTitle\"}")));
    }
}
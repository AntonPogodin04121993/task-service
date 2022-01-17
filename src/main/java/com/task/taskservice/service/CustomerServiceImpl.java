package com.task.taskservice.service;

import com.task.taskservice.dto.requestdto.RequestCustomerDto;
import com.task.taskservice.dto.responsedto.ResponseCustomerDto;
import com.task.taskservice.entity.Customer;
import com.task.taskservice.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private static final String CUSTOMER_NOT_FOUND_BY_ID_MESSAGE = "Customer with id: '%s' was not found";

    private final CustomerRepository customerRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ResponseCustomerDto> findAll(Integer page, Integer size) {
        val pageRequest = size == 0 ? Pageable.unpaged() : PageRequest.of(page, size);

        return customerRepository.findAllCustomers(pageRequest).get()
                .map(customer -> ResponseCustomerDto.builder()
                        .title(customer.getTitle())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void create(RequestCustomerDto requestCustomerDto) {
        Customer customer = Customer.builder().title(requestCustomerDto.getTitle()).build();
        customerRepository.save(customer);
        log.debug("The customer was saved in database");
    }

    @Override
    @Transactional
    public void deleteById(UUID customerId) {
        customerRepository.deleteCustomerById(customerId);
        log.debug("The customer was deleted from database");
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseCustomerDto findById(UUID customerId) {
        val customer = customerRepository.findCustomerById(customerId)
                .orElseThrow(() -> new EntityNotFoundException(String.format(CUSTOMER_NOT_FOUND_BY_ID_MESSAGE, customerId)));

        return ResponseCustomerDto.builder()
                .title(customer.getTitle())
                .build();
    }

    @Override
    @Transactional
    public void updateById(UUID customerId, RequestCustomerDto requestCustomerDto) {
        val customer = Customer.builder().title(requestCustomerDto.getTitle()).build();
        customerRepository.updateCustomerById(customerId, customer);
        log.debug("Customer was updated successfully");
    }
}

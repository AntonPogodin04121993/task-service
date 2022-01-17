package com.task.taskservice.controller;

import com.task.taskservice.dto.requestdto.RequestCustomerDto;
import com.task.taskservice.dto.responsedto.ResponseCustomerDto;
import com.task.taskservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/customers")
@Slf4j
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ResponseCustomerDto> findAll(
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "0") Integer size
    ) {
        log.info("Get request to find all customers");
        return customerService.findAll(page, size);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid RequestCustomerDto requestCustomerDto) {
        log.info("Get request to create customer");
        customerService.create(requestCustomerDto);
    }

    @DeleteMapping(path = "/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("customerId") UUID customerId) {
        log.info("Get request to delete customer by id: '{}'", customerId);
        customerService.deleteById(customerId);
    }

    @GetMapping("/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseCustomerDto findById(@PathVariable("customerId") UUID customerId) {
        log.info("Get request to find customer by id: '{}'", customerId);
        return customerService.findById(customerId);
    }

    @PutMapping(path = "/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateById(@PathVariable UUID customerId, @RequestBody @Valid RequestCustomerDto requestCustomerDto) {
        log.info("Get request to update customer with id: '{}'", customerId);
        customerService.updateById(customerId, requestCustomerDto);
    }

}

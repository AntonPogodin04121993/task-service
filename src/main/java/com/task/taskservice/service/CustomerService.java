package com.task.taskservice.service;

import com.task.taskservice.dto.requestdto.RequestCustomerDto;
import com.task.taskservice.dto.responsedto.ResponseCustomerDto;

import java.util.List;
import java.util.UUID;

public interface CustomerService {

    List<ResponseCustomerDto> findAll(Integer page, Integer size);

    void create(RequestCustomerDto requestCustomerDto);

    void deleteById(UUID customerId);

    ResponseCustomerDto findById(UUID customerId);

    void updateById(UUID customerId, RequestCustomerDto requestCustomerDto);

}

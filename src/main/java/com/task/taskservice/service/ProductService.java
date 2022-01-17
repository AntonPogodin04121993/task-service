package com.task.taskservice.service;

import com.task.taskservice.dto.requestdto.RequestProductDto;
import com.task.taskservice.dto.responsedto.ResponseProductDto;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    List<ResponseProductDto> findAll(UUID customerId, Integer page, Integer size);

    ResponseProductDto findById(UUID productId);

    void create(UUID customerId, RequestProductDto productDto);

    void updateById(UUID productId, RequestProductDto productDto);

    void deleteById(UUID productId);
}

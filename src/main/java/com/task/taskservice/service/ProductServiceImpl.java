package com.task.taskservice.service;

import com.task.taskservice.dto.requestdto.RequestProductDto;
import com.task.taskservice.dto.responsedto.ResponseProductDto;
import com.task.taskservice.entity.Product;
import com.task.taskservice.mapper.ProductMapper;
import com.task.taskservice.repository.CustomerRepository;
import com.task.taskservice.repository.ProductRepository;
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
public class ProductServiceImpl implements ProductService {

    private static final String PRODUCT_NOT_FOUND_MESSAGE = "Product with id '%s' was not found";
    private static final String CUSTOMER_NOT_FOUND_BY_ID_MESSAGE = "Customer with id: '%s' was not found";

    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    private final ProductMapper productMapper;

    @Override
    @Transactional(readOnly = true)
    public List<ResponseProductDto> findAll(UUID customerId, Integer page, Integer size) {
        val pageableRequest = size == 0 ? Pageable.unpaged() : PageRequest.of(page, size);

        return productRepository.findAllByCustomerId(pageableRequest, customerId).get()
                .map(product -> ResponseProductDto.builder()
                        .title(product.getTitle())
                        .description(product.getDescription())
                        .price(product.getPrice())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseProductDto findById(UUID productId) {
        val product = productRepository.findProductById(productId)
                .orElseThrow(() -> new EntityNotFoundException(String.format(PRODUCT_NOT_FOUND_MESSAGE, productId)));

        return ResponseProductDto.builder()
                .title(product.getTitle())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }

    @Override
    @Transactional
    public void create(UUID customerId, RequestProductDto productDto) {
        val product = Product.builder()
                .title(productDto.getTitle())
                .description(productDto.getDescription())
                .price(productDto.getPrice())
                .build();

        val customer = customerRepository.findCustomerById(customerId)
                .orElseThrow(() -> new EntityNotFoundException(String.format(CUSTOMER_NOT_FOUND_BY_ID_MESSAGE, customerId)));

        product.setCustomer(customer);

        productRepository.save(product);

        log.debug("The product was created successfully");
    }

    @Override
    @Transactional
    public void updateById(UUID productId, RequestProductDto productDto) {
        val product = productRepository.findProductById(productId)
                .orElseThrow(() -> new EntityNotFoundException(String.format(PRODUCT_NOT_FOUND_MESSAGE, productId)));

        productMapper.updateProductFromRequestProductDto(productDto, product);
        productRepository.save(product);
        log.debug("The product was updated successfully");
    }

    @Override
    @Transactional
    public void deleteById(UUID productId) {
        productRepository.deleteProductById(productId);
        log.debug("The product was deleted successfully");
    }
}

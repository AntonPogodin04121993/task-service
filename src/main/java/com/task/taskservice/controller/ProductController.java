package com.task.taskservice.controller;

import com.task.taskservice.dto.requestdto.RequestProductDto;
import com.task.taskservice.dto.responsedto.ResponseProductDto;
import com.task.taskservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@Slf4j
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping(path = "/customers/{customerId}/products")
    @ResponseStatus(HttpStatus.OK)
    public List<ResponseProductDto> findAll(
            @PathVariable("customerId") UUID customerId,
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "0") Integer size) {
        log.info("Get request to find all products by customer id: '{}'", customerId);
        return productService.findAll(customerId, page, size);
    }

    @GetMapping(path = "/products/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseProductDto findById(@PathVariable("productId") UUID productId) {
        log.info("Get request ti find product by id: '{}'", productId);
        return productService.findById(productId);
    }

    @PostMapping(path = "/customers/{customerId}/products")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@PathVariable("customerId") UUID customerId,
                       @RequestBody @Valid RequestProductDto productDto) {
        log.info("Get request to create product");
        productService.create(customerId, productDto);
    }

    @PutMapping(path = "/product/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateById(
            @PathVariable("productId") UUID productId,
            @RequestBody RequestProductDto productDto) {
        log.info("Get request to update product with id: '{}'", productId);
        productService.updateById(productId, productDto);
    }

    @DeleteMapping(path = "/products/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("productId") UUID productId) {
        log.info("Get request to delete product with id: '{}'", productId);
        productService.deleteById(productId);
    }

}

package com.task.taskservice.repository;

import com.task.taskservice.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

    @Query(value = "FROM Product p WHERE p.isDeleted = false AND p.customer.id = :customerId")
    Page<Product> findAllByCustomerId(Pageable pageable, @Param("customerId") UUID customerId);

    @Query(value = "FROM Product p WHERE p.isDeleted = false AND p.id = :productId")
    Optional<Product> findProductById(@Param("productId") UUID productId);

    @Modifying
    @Query(value = "UPDATE Product p SET p.isDeleted = true")
    void deleteProductById(@Param("productId") UUID productId);

}

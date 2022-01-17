package com.task.taskservice.repository;

import com.task.taskservice.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    @Query("FROM Customer c WHERE c.isDeleted = false")
    Page<Customer> findAllCustomers(Pageable pageable);

    @Query(value = "FROM Customer c WHERE c.isDeleted = false AND c.id = :customerId")
    Optional<Customer> findCustomerById(@Param("customerId") UUID customerId);

    @Modifying
    @Query(value = "UPDATE Customer c SET c.isDeleted = true WHERE c.id = :customerId")
    void deleteCustomerById(@Param("customerId") UUID customerId);

    @Modifying()
    @Query(value = "UPDATE Customer c SET c.modifiedAt = CURRENT_TIMESTAMP , c.title = :#{#customer.title} WHERE c.id = :customerId")
    void updateCustomerById(@Param("customerId") UUID customerId, @Param("customer") Customer customer);
}

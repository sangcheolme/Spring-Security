package com.study.springsecsection1.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.springsecsection1.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByEmail(String email);

}

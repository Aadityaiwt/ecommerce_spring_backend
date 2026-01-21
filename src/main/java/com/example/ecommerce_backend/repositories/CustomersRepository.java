package com.example.ecommerce_backend.repositories;

import com.example.ecommerce_backend.models.Customers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomersRepository extends JpaRepository<Customers, Long> {


}

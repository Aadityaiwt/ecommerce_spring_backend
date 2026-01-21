package com.example.ecommerce_backend.repositories;

import com.example.ecommerce_backend.models.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<Products, Long> {

}

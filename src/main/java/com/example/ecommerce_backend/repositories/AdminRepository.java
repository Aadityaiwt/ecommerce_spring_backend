package com.example.ecommerce_backend.repositories;

import com.example.ecommerce_backend.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Long> {

    Admin findByUsernameAndPassword(String username, String password);
}
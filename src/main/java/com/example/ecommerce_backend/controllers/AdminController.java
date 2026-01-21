package com.example.ecommerce_backend.controllers;

import com.example.ecommerce_backend.models.Admin;
import com.example.ecommerce_backend.repositories.AdminRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")

public class AdminController {
    AdminRepository adminRepository;
    public AdminController(AdminRepository adminRepository)
    {
        this.adminRepository = adminRepository;
    }

    @PostMapping("/admin")
    public ResponseEntity<?> login(@RequestBody Admin data)
    {
        Admin result = adminRepository.findByUsernameAndPassword(data.getUsername(), data.getPassword());
        if (result!=null)
        {
            return ResponseEntity.ok(Map.of(
                        "Status", "Successfully logged in",
                        "message", "Login Successfully",
                        "data", result
            ));
        }
        else
        {
            return ResponseEntity.ok(Map.of(
                    "Status", "error",
                    "message", "username and password is incorrect"
            ));
        }
    }
}

package com.example.ecommerce_backend.controllers;

import com.example.ecommerce_backend.models.Customers;
import com.example.ecommerce_backend.repositories.CustomersRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomersController {

    CustomersRepository cr;
    public CustomersController(CustomersRepository cr)
    {
        this.cr = cr;
    }

    @PostMapping("/customers")
    public Customers save(@RequestBody Customers data)
    {
        return cr.save(data);
    }

    @GetMapping("/customers")
    public List<Customers> getall()
    {
        return cr.findAll();
    }

    @PutMapping("/customers")
    public Customers update(@RequestBody Customers data)
    {
        return cr.save(data);
    }

    @DeleteMapping("/customers/{id}")
    public String delete(@PathVariable Long id)
    {
        cr.deleteById(id);
        return "Data Deleted Successfully";
    }

    @GetMapping("/customers/{id}")
    public Customers getsingle(@PathVariable Long id)
    {
        return cr.findById(id).orElse(null);
    }

}

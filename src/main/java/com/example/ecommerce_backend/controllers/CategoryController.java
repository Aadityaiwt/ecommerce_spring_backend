package com.example.ecommerce_backend.controllers;

import com.example.ecommerce_backend.models.Category;
import com.example.ecommerce_backend.repositories.CategoryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")

public class CategoryController {
    CategoryRepository categoryRepository;
    public CategoryController(CategoryRepository categoryRepository)
    {
        this.categoryRepository = categoryRepository;
    }

    @PostMapping("/category")
    public Category create(@RequestBody Category data)
    {
        return categoryRepository.save(data);
    }

    @GetMapping("/category")
    public List<Category> showCategory()
    {
        return categoryRepository.findAll();
    }

    @GetMapping("/category{id}")
    public Category singleCategory(@PathVariable Long id)
    {
        return categoryRepository.findById(id).orElse(null);
    }

    @DeleteMapping("/category")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id)
    {
        categoryRepository.deleteById(id);
        return ResponseEntity.ok(Map.of(
                "status", "success",
                "message", "Category Deleted Successfully"
        ));
    }

    @PutMapping("/category")
    public Category updateCategory(@RequestBody Category data)
    {
        return categoryRepository.save(data);
    }
}

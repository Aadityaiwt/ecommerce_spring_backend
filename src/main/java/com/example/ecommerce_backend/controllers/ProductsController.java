package com.example.ecommerce_backend.controllers;

import com.example.ecommerce_backend.models.Products;
import com.example.ecommerce_backend.repositories.ProductsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ProductsController {

    private static final Logger log = LoggerFactory.getLogger(ProductsController.class);
    ProductsRepository productsRepository;

    public ProductsController(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @PostMapping("/product")
    public Products createProduct(
            @RequestParam("category") String category,
            @RequestParam("name") String name,
            @RequestParam("mrp") String mrp,
            @RequestParam("seleprice") String saleprice,
            @RequestParam("description") String description,
            @RequestParam("image1") MultipartFile image1,
            @RequestParam("image2") MultipartFile image2
    ) throws IOException {

        // upload first file
        String foldername = "upload/";
        String filename1 = System.currentTimeMillis() + "_" + image1.getOriginalFilename();
        Path path1 = Paths.get(foldername, filename1);
        Files.write(path1, image1.getBytes());

        // upload second file
        String filename2 = System.currentTimeMillis() + "_" + image2.getOriginalFilename();
        Path path2 = Paths.get(foldername, filename2);
        Files.write(path2, image2.getBytes());

        // create model object
        Products data = new Products();
        data.setCategoryName(category);
        data.setName(name);
        data.setMrp(mrp);
        data.setSaleprice(saleprice);
        data.setDescription(description);
        data.setImage1(filename1);
        data.setImage2(filename2);

        return productsRepository.save(data);
    }

    @GetMapping("/product")
    public List<Products> showAllProducts() {
        return productsRepository.findAll();
    }

    @GetMapping("/product/{id}")
    public Products getSingleProduct(@PathVariable Long id) {
        return productsRepository.findById(id).orElse(null);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        productsRepository.deleteById(id);
        return ResponseEntity.ok(Map.of(
                "status", "success",
                "message", "Product Deleted Successfully"
        ));
    }

    @PutMapping("/product")
    public Products updateProduct(
            @RequestParam("id") Long id,
            @RequestParam("category") String category,
            @RequestParam("name") String name,
            @RequestParam("mrp") String mrp,
            @RequestParam("saleprice") String saleprice,
            @RequestParam("description") String description,
            @RequestParam(value = "image1", required = false) MultipartFile image1,
            @RequestParam(value = "image2", required = false) MultipartFile image2

    ) {
        String filename1 = null;
        String filename2 = null;
        String foldername = "upload/";
        if (image1 != null) {
            filename1 = System.currentTimeMillis() + "_" + image1.getOriginalFilename();
            Path path1 = Paths.get(foldername, filename1);
            try {
                Files.write(path1, image1.getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        if (image2 != null) {
            filename2 = System.currentTimeMillis() + "_" + image2.getOriginalFilename();
            Path path2 = Paths.get(foldername, filename2);
            try {
                Files.write(path2, image2.getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        Products data = productsRepository.findById(id).orElse(null);

        if (data != null) {
            data.setName(name);
            data.setMrp(mrp);
            data.setCategoryName(category);
            data.setSaleprice(saleprice);
            data.setDescription(description);

            log.info("File name 1 = " + filename1);
            log.info("File name 2 = " + filename2);

            if (filename1 != null) {
                data.setImage1((filename1));
            }

            if (filename2 != null) {
                data.setImage2((filename2));
            }
            return productsRepository.save(data);

        } else {
            return null;
        }
    }
}

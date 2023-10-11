package com.example.businessInventoryapi.controller;

import com.example.businessInventoryapi.model.Product;
import com.example.businessInventoryapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/product")
public class ProductController {
    private final ProductService productService;
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping("allproducts")
    public ResponseEntity<List<Product>> getAllProducts() {
        return productService.getAllProducts();
    }
    @GetMapping("allproducts/{id}")
    public Optional<Product> getProductWithSpecifiedId(@PathVariable Long id) {
        return productService.getProductWithSpecifiedId(id);
    }
    @PostMapping
    public ResponseEntity<String> addNewProduct(@RequestBody Product product) {
        return productService.addNewProduct(product);
        //return null;
    }
    @DeleteMapping(path = "{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable("productId") Long productId) {
        return productService.deleteProduct(productId);
    }
    @PutMapping(path = "allproducts/{productId}")
    public ResponseEntity<String> updateProduct(@PathVariable("productId") Long productId,
                              @RequestParam(required = false) String name,
                              @RequestParam(required = false) String description,
                              @RequestParam(required = false) Long quantity,
                              @RequestParam(required = false) Double weight,
                              @RequestParam(required = false) Double price) {
        return productService.updateProduct(productId, name, description, quantity, weight, price);

    }

}

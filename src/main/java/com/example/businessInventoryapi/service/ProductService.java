package com.example.businessInventoryapi.service;

import com.example.businessInventoryapi.dao.ProductRepository;
import com.example.businessInventoryapi.model.Product;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ResponseEntity<List<Product>> getAllProducts() {
        try {
            return new ResponseEntity<>(productRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addNewProduct(Product product) {
        try {
            productRepository.save(product);
            return new ResponseEntity<>("success", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("failed", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> deleteProduct(Long productId) {
        try {

            boolean product = productRepository.existsById(productId);

            if (!product) {
                throw new IllegalStateException(
                        "The product with the product id " + productId + " you're requesting to delete " +
                                "does not exist"
                );
            }
            productRepository.deleteById(productId);
            return new ResponseEntity<>("success", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("failed", HttpStatus.BAD_REQUEST);
    }

    @Transactional
    public ResponseEntity<String> updateProduct(Long productId,
                                                String name,
                                                String description,
                                                Long quantity,
                                                Double weight,
                                                Double price) {
        try {
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new IllegalStateException(
                            "The product with Id " + productId + " you want to update, does not exist")
                    );

            if (name != null && name.length() > 0 && !Objects.equals(product.getName(), name)) {
                product.setName(name);
            }

            if (description != null && description.length() > 0 && !Objects.equals(product.getDescription(), description)) {
                product.setDescription(description);
            }

            if (quantity != null) {
                product.setQuantity(quantity);
            }

            if (weight != null) {
                product.setWeight(weight);
            }

            if (price != null) {
                product.setPrice(price);
            }
            return new ResponseEntity<>("success", HttpStatus.ACCEPTED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Bad request", HttpStatus.BAD_REQUEST);
    }

    public Optional<Product> getProductWithSpecifiedId(Long id) {
        boolean product = productRepository.existsById(id);

        if (!product) {
            throw new IllegalStateException("product with id " + id + " is not in the records");
        }

        return productRepository.findById(id);
    }
}

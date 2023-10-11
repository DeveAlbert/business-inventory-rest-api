package com.example.businessInventoryapi.model;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table
public class Product {
    @Id
    @SequenceGenerator(name = "product_sequence", sequenceName = "product_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_sequence")
    private Long id;
    private String name;
    private String description;
    private Long quantity;
    private Double weight;
    private Double price;

}

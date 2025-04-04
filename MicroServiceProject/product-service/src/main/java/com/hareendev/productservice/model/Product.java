package com.hareendev.productservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name="productTable")
public class Product {
    @Id
    private int id;
    private int productId;
    private String productName;
    private String description;
    private int forSale;
}

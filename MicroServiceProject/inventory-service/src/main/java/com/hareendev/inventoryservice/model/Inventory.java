package com.hareendev.inventoryservice.model;

import jakarta.persistence.Column;
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
@Table(name="inventoryTable")
public class Inventory {
    @Id
    private int id;

    @Column(name = "item_id")
    private int itemId;

    @Column(name = "product_id")
    private int productId;
    private int quantity;
}

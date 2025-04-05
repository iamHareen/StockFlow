package com.hareendev.inventoryservice.repo;

import com.hareendev.inventoryservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepo extends JpaRepository<Inventory, Integer> {
    Inventory findByItemId(Integer itemId);
}

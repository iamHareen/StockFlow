package com.hareendev.inventoryservice.repo;

//import org.springframework.data.jpa.repository.Query;
import com.hareendev.inventoryservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepo extends JpaRepository<Inventory, Integer> {
//    @Query(value = "SELECT * FROM inventory WHERE item_id = ?1", nativeQuery = true)
//    Inventory getItemById(Integer itemId);
}

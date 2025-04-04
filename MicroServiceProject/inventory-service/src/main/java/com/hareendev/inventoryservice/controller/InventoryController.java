package com.hareendev.inventoryservice.controller;

import com.hareendev.inventoryservice.dto.InventoryDTO;
import com.hareendev.inventoryservice.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "api/v1/")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/getitems")
    public List<InventoryDTO> getItems() {
        return inventoryService.getAllItems();
    }

    @PostMapping("/additem")
    public InventoryDTO saveItem(@RequestBody InventoryDTO inventoryDTO) {
        return inventoryService.saveItem(inventoryDTO);
    }

    @PutMapping("/updateitem")
    public InventoryDTO updateItem(@RequestBody InventoryDTO inventoryDTO) {
        return inventoryService.updateItem(inventoryDTO);
    }

    @DeleteMapping("/deleteitem/{itemId}")
    public String deleteItem(@PathVariable Integer itemId) {
        return inventoryService.deleteItem(itemId);
    }

//    @GetMapping("/item/{itemId}")
//    public InventoryDTO getItemById(@PathVariable Integer itemId) {
//        return inventoryService.getItemById(itemId);
//    }
}

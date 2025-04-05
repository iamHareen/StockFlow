package com.hareendev.inventoryservice.service;

import com.hareendev.inventoryservice.dto.InventoryDTO;
import com.hareendev.inventoryservice.model.Inventory;
import com.hareendev.inventoryservice.repo.InventoryRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class InventoryService {
    @Autowired
    private InventoryRepo inventoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    public List<InventoryDTO> getAllItems() {
        List<Inventory> itemList = inventoryRepo.findAll();
        return modelMapper.map(itemList, new TypeToken<List<InventoryDTO>>(){}.getType());
    }

    public InventoryDTO saveItem(InventoryDTO inventoryDTO) {
        inventoryRepo.save(modelMapper.map(inventoryDTO, Inventory.class));
        return inventoryDTO;
    }

    public InventoryDTO updateItem(InventoryDTO inventoryDTO) {
        inventoryRepo.save(modelMapper.map(inventoryDTO, Inventory.class));
        return inventoryDTO;
    }

    public String deleteItem(Integer itemId) {
        inventoryRepo.deleteById(itemId);
        return "Inventory Item deleted";
    }

    public InventoryDTO getItemById(Integer itemId) {
        Inventory item = inventoryRepo.findByItemId(itemId);
        return modelMapper.map(item, InventoryDTO.class);
    }
}

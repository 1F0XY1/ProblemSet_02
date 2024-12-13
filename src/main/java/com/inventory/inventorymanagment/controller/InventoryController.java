package com.inventory.inventorymanagment.controller;

import com.inventory.inventorymanagment.model.Product;
import com.inventory.inventorymanagment.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    // Add stock to a product
    @PostMapping("/add/{productId}")
    public ResponseEntity<Product> addStock(@PathVariable Long productId, @RequestParam int quantity) {
        return ResponseEntity.ok(inventoryService.addStock(productId, quantity));
    }

    // Deduct stock from a product
    @PostMapping("/deduct/{productId}")
    public ResponseEntity<Product> deductStock(@PathVariable Long productId, @RequestParam int quantity) {
        return ResponseEntity.ok(inventoryService.deductStock(productId, quantity));
    }

    // Get the current stock level of a product
    @GetMapping("/stock/{productId}")
    public ResponseEntity<Integer> checkStockLevel(@PathVariable Long productId) {
        return ResponseEntity.ok(inventoryService.checkStockLevel(productId));
    }

    // Get the stock level of a product by its ID
    @GetMapping("/{productId}")
    public ResponseEntity<Integer> getStockLevel(@PathVariable Long productId) {
        return ResponseEntity.ok(inventoryService.checkStockLevel(productId));
    }
}

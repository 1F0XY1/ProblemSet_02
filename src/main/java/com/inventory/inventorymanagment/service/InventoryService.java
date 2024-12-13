package com.inventory.inventorymanagment.service;

import com.inventory.inventorymanagment.model.Product;
import com.inventory.inventorymanagment.repository.ProductRepository;
import com.inventory.inventorymanagment.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    @Autowired
    private ProductRepository productRepository;

    // Add stock to a product
    public Product addStock(Long productId, int quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "ID", productId.toString()));

        product.setStockLevel(product.getStockLevel() + quantity);
        return productRepository.save(product);
    }

    // Deduct stock from a product
    public Product deductStock(Long productId, int quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "ID", productId.toString()));

        if (product.getStockLevel() < quantity) {
            throw new IllegalArgumentException("Not enough stock to deduct");
        }

        product.setStockLevel(product.getStockLevel() - quantity);
        return productRepository.save(product);
    }

    // Get current stock level for a product
    public int checkStockLevel(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "ID", productId.toString()));

        return product.getStockLevel();
    }
}

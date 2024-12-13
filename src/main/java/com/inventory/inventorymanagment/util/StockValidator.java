package com.inventory.inventorymanagment.util;

import org.springframework.stereotype.Component;

@Component
public class StockValidator {

    // Validates that the stock level is sufficient to deduct the requested quantity
    public void validateDeductQuantity(int currentStockLevel, int quantityToDeduct) {
        if (currentStockLevel < quantityToDeduct) {
            throw new IllegalArgumentException("Not enough stock available to deduct. Current stock: "
                    + currentStockLevel + ", Requested deduction: " + quantityToDeduct);
        }
    }

    // Validates that the quantity to add is a positive number
    public void validateAddQuantity(int quantityToAdd) {
        if (quantityToAdd <= 0) {
            throw new IllegalArgumentException("Quantity to add must be greater than 0.");
        }
    }
}

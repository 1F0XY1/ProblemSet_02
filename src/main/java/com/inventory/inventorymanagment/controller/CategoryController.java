package com.inventory.inventorymanagment.controller;

import com.inventory.inventorymanagment.dto.CategoryDTO;
import com.inventory.inventorymanagment.model.Category;
import com.inventory.inventorymanagment.model.Product;
import com.inventory.inventorymanagment.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // Create a new category
    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());
        Category savedCategory = categoryService.createCategory(category);

        CategoryDTO responseDTO = new CategoryDTO(savedCategory.getId(), savedCategory.getName());
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    // Get all categories
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        List<CategoryDTO> categoryDTOs = categories.stream()
                .map(category -> new CategoryDTO(category.getId(), category.getName()))
                .collect(Collectors.toList());
        return new ResponseEntity<>(categoryDTOs, HttpStatus.OK);
    }

    // Get category by ID
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long id) {
        Category category = categoryService.getCategoryById(id);
        if (category != null) {
            CategoryDTO categoryDTO = new CategoryDTO(category.getId(), category.getName());
            return new ResponseEntity<>(categoryDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Update a category
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long id, @Valid @RequestBody CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setId(id);
        category.setName(categoryDTO.getName());
        Category updatedCategory = categoryService.updateCategory(id, category);

        if (updatedCategory != null) {
            CategoryDTO responseDTO = new CategoryDTO(updatedCategory.getId(), updatedCategory.getName());
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Get products by category ID
    @GetMapping("/{categoryId}/products")
    public ResponseEntity<List<Product>> getProductsByCategoryId(@PathVariable Long categoryId) {
        List<Product> products = categoryService.getProductsByCategoryId(categoryId);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // Example endpoint to use the example method
    @GetMapping("/example/{id}")
    public ResponseEntity<CategoryDTO> exampleEndpoint(@PathVariable Long id) {
        CategoryDTO categoryDTO = categoryService.exampleMethod(id);
        return categoryDTO != null ? new ResponseEntity<>(categoryDTO, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Delete a category
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

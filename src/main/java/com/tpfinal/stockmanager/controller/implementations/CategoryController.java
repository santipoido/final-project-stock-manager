package com.tpfinal.stockmanager.controller.implementations;

import com.tpfinal.stockmanager.model.implementations.Category;
import com.tpfinal.stockmanager.model.implementations.Product;
import com.tpfinal.stockmanager.service.implementations.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@PreAuthorize("hasAnyRole('ADMIN', 'SELLER')")
@Tag(name = "\uD83D\uDDC2\uFE0FCategories", description = "Operations related to categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Operation(summary = "Create a category")
    @PostMapping("/create")
    public ResponseEntity<?> createCategory(@Valid @RequestBody Category category){
        Category newCategory = categoryService.create(category);
        return ResponseEntity.ok(newCategory);
    }

    @Operation(summary = "Update a category")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Integer id, @RequestBody Category dto) {
        Category updateCategory = categoryService.update(id, dto);
        return ResponseEntity.ok(updateCategory);
    }

    @Operation(summary = "Delete a category")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Integer id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get all categories")
    @GetMapping("/get")
    public ResponseEntity<List<Category>> listCategories() {
        List<Category> categories = categoryService.findAll();
        return ResponseEntity.ok(categories);
    }

    @Operation(summary = "Get a category by name")
    @GetMapping("/get/by-name")
    public ResponseEntity<Category> getCategoryByName(@RequestParam String name) {
        return categoryService.findOptionalByName(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

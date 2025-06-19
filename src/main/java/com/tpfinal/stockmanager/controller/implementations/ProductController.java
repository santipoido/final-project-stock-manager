package com.tpfinal.stockmanager.controller.implementations;

import com.tpfinal.stockmanager.model.implementations.Product;
import com.tpfinal.stockmanager.service.implementations.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@PreAuthorize("hasAnyRole('ADMIN', 'SELLER')")
@Tag(name = "Products", description = "Operations related to products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Operation(summary = "Create a product")
    @PostMapping
    public ResponseEntity<?> createProduct(@Valid @RequestBody Product product){
        Product newProduct = productService.create(product);
        return ResponseEntity.ok(newProduct);
    }

    @Operation(summary = "Update a product")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Integer id, @RequestBody Product dto) {
        Product updatedProduct = productService.update(id, dto);
        return ResponseEntity.ok(updatedProduct);
    }

    @Operation(summary = "Delete a product")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get all products")
    @GetMapping
    public ResponseEntity<List<Product>> listProducts() {
        List<Product> products = productService.findAll();
        return ResponseEntity.ok(products);
    }

    @Operation(summary = "Get product by name")
    @GetMapping("/by-name")
    public ResponseEntity<Product> getProductByName(@RequestParam String name) {
        return productService.findOptionalByName(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

package com.tpfinal.stockmanager.controller.implementations;

import com.tpfinal.stockmanager.model.implementations.Product;
import com.tpfinal.stockmanager.service.implementations.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
@PreAuthorize("hasAnyRole('ADMIN', 'SELLER')")
@Tag(name = "\uD83D\uDED2Products", description = "Operations related to products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Operation(summary = "Create a product")
    @PostMapping("/create")
    public ResponseEntity<?> createProduct(@Valid @RequestBody Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(err -> errors.put(err.getField(), err.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);
        }
        Product newProduct = productService.create(product);
        return ResponseEntity.ok(newProduct);
    }

    @Operation(summary = "Update a product")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Integer id, @Valid @RequestBody Product dto) {
        Product updatedProduct = productService.update(id, dto);
        return ResponseEntity.ok(updatedProduct);
    }

    @Operation(summary = "Delete a product")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get all products")
    @GetMapping("/get")
    public ResponseEntity<List<Product>> listProducts() {
        List<Product> products = productService.findAll();
        return ResponseEntity.ok(products);
    }

    @Operation(summary = "Get product by name")
    @GetMapping("/get/by-name")
    public ResponseEntity<Product> getProductByName(@RequestParam String name) {
        return productService.findOptionalByName(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get product by ID")
    @GetMapping("/get/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer id) {
        Product product = productService.findById(id);
        return ResponseEntity.ok(product);
    }

    @Operation(summary = "Get products without stock")
    @GetMapping("/get/without-stock")
    public ResponseEntity<List<Product>> getProductsWithoutStock() {
        List<Product> products = productService.getProductsWithoutStock();
        return ResponseEntity.ok(products);
    }

    @Operation(summary = "Get products by category")
    @GetMapping("/get/by-category/{name}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable String name) {
        List<Product> products = productService.getProductsByCategory(name);
        return ResponseEntity.ok(products);
    }
}

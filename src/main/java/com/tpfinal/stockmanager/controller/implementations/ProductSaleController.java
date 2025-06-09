package com.tpfinal.stockmanager.controller.implementations;

import com.tpfinal.stockmanager.model.implementations.Product;
import com.tpfinal.stockmanager.model.implementations.ProductSale;
import com.tpfinal.stockmanager.model.implementations.ProductSaleDTO;
import com.tpfinal.stockmanager.service.implementations.ProductSaleService;
import com.tpfinal.stockmanager.service.implementations.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productsale")
@PreAuthorize("hasAnyRole('ADMIN', 'SELLER')")
public class ProductSaleController {

    @Autowired
    private ProductSaleService productSaleService;

    @PostMapping
    public ResponseEntity<?> createProductSale(@Valid @RequestBody ProductSale product){
        ProductSale newProductSale = productSaleService.create(product);
        return ResponseEntity.ok(newProductSale);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProductSale(@PathVariable Integer id, @RequestBody ProductSale dto) {
        ProductSale updatedProductSale = productSaleService.update(id, dto);
        return ResponseEntity.ok(updatedProductSale);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProductSale(@PathVariable Integer id) {
        productSaleService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ProductSaleDTO>> listProductsSale() {
        List<ProductSaleDTO> productsSale = productSaleService.findAllDTO();
        return ResponseEntity.ok(productsSale);
    }
}

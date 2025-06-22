package com.tpfinal.stockmanager.controller.implementations;

import com.tpfinal.stockmanager.model.dto.SaleRequestDTO;
import com.tpfinal.stockmanager.model.implementations.Product;
import com.tpfinal.stockmanager.model.implementations.Sale;
import com.tpfinal.stockmanager.model.implementations.User;
import com.tpfinal.stockmanager.service.implementations.SaleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sales")
@PreAuthorize("hasAnyRole('ADMIN', 'SELLER')")
@Tag(name = "Sales", description = "Operations related to sales")
public class SaleController {

    private final SaleService saleService;

    @PostMapping("/create")
    public ResponseEntity<Sale> createSale(@RequestBody SaleRequestDTO saleRequest) {
        Sale sale = saleService.create(saleRequest);
        return ResponseEntity.ok(sale);
    }

    @GetMapping("/get")
    public ResponseEntity<List<Sale>> listSales() {
        List<Sale> sales = saleService.findAll();
        return ResponseEntity.ok(sales);
    }

}



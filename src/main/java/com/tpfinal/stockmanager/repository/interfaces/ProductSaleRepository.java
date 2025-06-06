package com.tpfinal.stockmanager.repository.interfaces;

import com.tpfinal.stockmanager.model.implementations.Category;
import com.tpfinal.stockmanager.model.implementations.ProductSale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductSaleRepository extends JpaRepository<ProductSale, Integer> {
    Optional<ProductSale> findByName(String name);
}

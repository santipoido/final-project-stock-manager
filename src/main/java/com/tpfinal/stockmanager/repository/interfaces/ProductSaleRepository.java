package com.tpfinal.stockmanager.repository.interfaces;

import com.tpfinal.stockmanager.model.implementations.Category;
import com.tpfinal.stockmanager.model.implementations.ProductSale;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductSaleRepository extends JpaRepository<ProductSale, Integer> {
}

package com.tpfinal.stockmanager.repository.interfaces;

import com.tpfinal.stockmanager.model.implementations.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}

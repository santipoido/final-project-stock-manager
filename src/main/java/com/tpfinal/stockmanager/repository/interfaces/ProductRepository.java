package com.tpfinal.stockmanager.repository.interfaces;

import com.tpfinal.stockmanager.model.implementations.Category;
import com.tpfinal.stockmanager.model.implementations.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer>{
    Optional<Product> findByName(String name);
    boolean existsByName(String name);

    @Query("SELECT p FROM Product p WHERE p.stock = 0")
    List<Product> findAllWithoutStock();

    @Query("SELECT p FROM Product p JOIN p.category c WHERE LOWER(c.name) = LOWER(:name)")
    List<Product> getProductsByCategory(@Param("name") String name);
}

package com.tpfinal.stockmanager.repository.interfaces;

import com.tpfinal.stockmanager.model.implementations.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}

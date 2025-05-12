package com.tpfinal.stockmanager.service.implementations;

import com.tpfinal.stockmanager.controller.implementations.CategoryController;
import com.tpfinal.stockmanager.model.implementations.Category;
import com.tpfinal.stockmanager.model.implementations.Product;
import com.tpfinal.stockmanager.repository.interfaces.CategoryRepository;
import com.tpfinal.stockmanager.repository.interfaces.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    public void deleteCategory(int id) {
        categoryRepository.deleteById(id);
    }

    public Optional<Category> getCategoryById(int id) {
        return categoryRepository.findById(id);
    }
}

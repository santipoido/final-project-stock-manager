package com.tpfinal.stockmanager.service.implementations;
import com.tpfinal.stockmanager.exceptions.entityAlreadyExists;
import com.tpfinal.stockmanager.model.implementations.Category;
import com.tpfinal.stockmanager.repository.interfaces.CategoryRepository;
import com.tpfinal.stockmanager.service.interfaces.IntCategoryService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements IntCategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> findOptionalById(Integer integer) {
        return categoryRepository.findById(integer);
    }

    @Override
    public Category findById(Integer id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entidad no encontrada con id: " + id));
    }

    @Override
    public Category create(Category entity) throws entityAlreadyExists {
        if(!categoryRepository.existsById(entity.getId())){
            return categoryRepository.save(entity);
        } else {
            throw new entityAlreadyExists("El categoria que intentas crear ya existe");

        }
    }

    @Override
    @Transactional
    public Category update(Integer id, Category entityDetails) {
        Category existingEntity = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entity not found"));

        existingEntity.setCategoryName(entityDetails.getCategoryName());
        existingEntity.setProducts(entityDetails.getProducts());
        return categoryRepository.save(existingEntity);
    }


    @Override
    public void delete(Integer id) {
        categoryRepository.deleteById(id);
    }
}

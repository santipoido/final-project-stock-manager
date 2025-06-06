package com.tpfinal.stockmanager.service.implementations;
import com.tpfinal.stockmanager.model.implementations.Category;
import com.tpfinal.stockmanager.repository.interfaces.CategoryRepository;
import com.tpfinal.stockmanager.service.interfaces.IntCategoryService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements IntCategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

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
    public Optional<Category> findOptionalByName(String name) {
        return categoryRepository.findBycategoryName(name);
    }


    @Override
    public Category create(Category entity) throws EntityExistsException {
        if(!categoryRepository.existsById(entity.getId())){
            return categoryRepository.save(entity);
        } else {
            throw new EntityExistsException("El categoria que intentas crear ya existe");

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

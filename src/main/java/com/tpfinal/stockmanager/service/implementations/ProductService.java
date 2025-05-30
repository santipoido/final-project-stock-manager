package com.tpfinal.stockmanager.service.implementations;

import com.tpfinal.stockmanager.exceptions.entityAlreadyExists;
import com.tpfinal.stockmanager.model.implementations.Product;
import com.tpfinal.stockmanager.repository.interfaces.ProductRepository;
import com.tpfinal.stockmanager.service.interfaces.IntProductService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IntProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findOptionalById(Integer id) throws EntityNotFoundException {
        return productRepository.findById(id);
    }

    @Override
    public Product findById(Integer id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entidad no encontrada con id: " + id));
    }

    @Override
    public Product create(Product entity) throws entityAlreadyExists {
        if(!productRepository.existsById(entity.getId())) {
            return productRepository.save(entity);

        }else {
            throw new entityAlreadyExists("El producto ya existe");
        }
    }

    @Override
    @Transactional
    public Product update(Integer id, Product entityDetails) {
        Product existingEntity = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entity not found"));

        existingEntity.setProductName(entityDetails.getProductName());
        existingEntity.setCategory(entityDetails.getCategory());
        existingEntity.setPrice(entityDetails.getPrice());
        existingEntity.setStock(entityDetails.getStock());
        return productRepository.save(existingEntity);
    }

    @Override
    public void delete(Integer id) throws EntityNotFoundException {
        Product product = productRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Entity not found"));

        productRepository.deleteById(id);
    }
}

package com.tpfinal.stockmanager.service.implementations;

import com.tpfinal.stockmanager.exceptions.entityAlreadyExists;
import com.tpfinal.stockmanager.model.implementations.Product;
import com.tpfinal.stockmanager.model.implementations.ProductSale;
import com.tpfinal.stockmanager.repository.interfaces.ProductSaleRepository;
import com.tpfinal.stockmanager.service.interfaces.IntProductSaleService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

public class ProductSaleService implements IntProductSaleService {
    private final ProductSaleRepository productSaleRepository;

    public ProductSaleService(ProductSaleRepository productSaleRepository) {
        this.productSaleRepository = productSaleRepository;
    }

    @Override
    public List<ProductSale> findAll() {
        return productSaleRepository.findAll();
    }

    @Override
    public Optional<ProductSale> findOptionalById(Integer id) throws EntityNotFoundException {
        return productSaleRepository.findById(id);
    }

    @Override
    public ProductSale findById(Integer id) {
        return productSaleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entidad no encontrada con id: " + id));
    }

    @Override
    public ProductSale create(ProductSale entity) throws EntityExistsException {
        if(!productSaleRepository.existsById(entity.getId())) {
            return productSaleRepository.save(entity);

        }else {
            throw new EntityNotFoundException("The product sale already exists");
        }
    }

    @Override
    public ProductSale update(Integer id, ProductSale entity) {
        ProductSale existingEntity = productSaleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entity not found"));

        existingEntity.setId(entity.getId());
        existingEntity.setUser(entity.getUser());
        existingEntity.setProduct(entity.getProduct());
        existingEntity.setQuantity(entity.getQuantity());
        existingEntity.setSaleDate(entity.getSaleDate());
        existingEntity.setUnitPrice(entity.getUnitPrice());

        return productSaleRepository.save(existingEntity);
    }

    @Override
    public void delete(Integer id) throws EntityNotFoundException {
        ProductSale productSale = productSaleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found"));

        productSaleRepository.deleteById(id);
    }
}

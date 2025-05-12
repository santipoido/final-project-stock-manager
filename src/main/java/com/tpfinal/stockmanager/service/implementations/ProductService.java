package com.tpfinal.stockmanager.service.implementations;

import com.tpfinal.stockmanager.model.implementations.Product;
import com.tpfinal.stockmanager.repository.interfaces.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }

    public Optional<Product> getProductById(int id) {
        return productRepository.findById(id);
    }
}

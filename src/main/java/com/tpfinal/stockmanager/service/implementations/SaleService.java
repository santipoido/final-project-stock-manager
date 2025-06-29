package com.tpfinal.stockmanager.service.implementations;

import com.tpfinal.stockmanager.model.dto.ProductQuantityDTO;
import com.tpfinal.stockmanager.model.dto.SaleRequestDTO;
import com.tpfinal.stockmanager.model.implementations.Product;
import com.tpfinal.stockmanager.model.implementations.Sale;
import com.tpfinal.stockmanager.model.implementations.SaleDetail;
import com.tpfinal.stockmanager.model.implementations.User;
import com.tpfinal.stockmanager.repository.interfaces.ProductRepository;
import com.tpfinal.stockmanager.repository.interfaces.SaleRepository;
import com.tpfinal.stockmanager.repository.interfaces.UserRepository;
import com.tpfinal.stockmanager.service.interfaces.IntSaleService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SaleService implements IntSaleService {

    private final ProductRepository productRepository;
    private final SaleRepository saleRepository;
    private final UserRepository userRepository;
    private final DolarService dolarService;

    @Transactional
    public Sale create(SaleRequestDTO saleRequest) {
        User user = userRepository.findById(saleRequest.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Sale sale = Sale.builder()
                .date(LocalDateTime.now())
                .user(user)
                .build();

        List<SaleDetail> details = new ArrayList<>();
        double total = 0.0;

        for (ProductQuantityDTO detailRequest : saleRequest.getProducts()) {
            Product product = productRepository.findById(detailRequest.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found with id " + detailRequest.getProductId()));

            if (product.getStock() < detailRequest.getQuantity()) {
                throw new RuntimeException("Not enough stock for product: " + product.getName());
            }

            product.setStock(product.getStock() - detailRequest.getQuantity());
            productRepository.save(product);

            double subtotal = product.getPrice() * detailRequest.getQuantity();
            total += subtotal;

            SaleDetail detail = SaleDetail.builder()
                    .product(product)
                    .quantity(detailRequest.getQuantity())
                    .unitPrice(product.getPrice())
                    .sale(sale)
                    .build();

            details.add(detail);
        }

        sale.setDetails(details);
        sale.setTotal(total);

        double dolarRate = dolarService.obtenerCotizacionDolarOficial().doubleValue();
        sale.setTotalUsd(total / dolarRate);

        return saleRepository.save(sale);
    }


    @Override
    public List<Sale> findAll() {
        return saleRepository.findAll();
    }

    @Override
    public Optional<Sale> findOptionalById(Integer id) {
        return saleRepository.findById(id);
    }

    @Override
    public Sale findById(Integer id) {
        return saleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sale not found with id: " + id));
    }

    @Override
    public Optional<Sale> findOptionalByName(String name) {
        return Optional.empty();
    }

    @Override
    public Sale findByName(String name) {
        return null;
    }

    @Override
    public Sale create(Sale entity) {
        return null;
    }

    @Override
    public Sale update(Integer integer, Sale entity) {
        return null;
    }

    @Override
    public void delete(Integer id) {
        Sale sale = saleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sale with ID " + id + " was not found"));

        saleRepository.delete(sale);
    }
}


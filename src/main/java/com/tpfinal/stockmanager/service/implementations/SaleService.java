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

    @Transactional
    public Sale createSale(SaleRequestDTO saleRequest) {
        User user = userRepository.findById(saleRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Sale sale = Sale.builder()
                .date(LocalDateTime.now())
                .user(user)
                .build();

        List<SaleDetail> details = new ArrayList<>();

        for (ProductQuantityDTO detailRequest : saleRequest.getProducts()) {
            Product product = productRepository.findById(detailRequest.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found with id " + detailRequest.getProductId()));

            if (product.getStock() < detailRequest.getQuantity()) {
                throw new RuntimeException("Not enough stock for product: " + product.getName());
            }

            product.setStock(product.getStock() - detailRequest.getQuantity());
            productRepository.save(product);

            SaleDetail detail = SaleDetail.builder()
                    .product(product)
                    .quantity(detailRequest.getQuantity())
                    .unitPrice(product.getPrice())
                    .sale(sale)
                    .build();

            details.add(detail);
        }

        sale.setDetails(details);

        return saleRepository.save(sale);
    }


    @Override
    public List<Sale> findAll() {
        return saleRepository.findAll();
    }

    @Override
    public Optional<Sale> findOptionalById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public Sale findById(Integer integer) {
        return null;
    }

    @Override
    public Optional<Sale> findOptionalByName(String name) {
        return Optional.empty();
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
    public void delete(Integer integer) {

    }
}


package com.tpfinal.stockmanager.model.implementations;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Getter
@Setter
public class ProductSaleDTO {
    private Integer id;
    private Integer productId;
    private Integer userId;
    private int quantity;
    private float unitPrice;
    private LocalDateTime saleDate;

    public ProductSaleDTO(ProductSale sale) {
        this.id = sale.getId();
        this.productId = sale.getProduct().getId();
        this.userId = sale.getUser().getId();
        this.quantity = sale.getQuantity();
        this.unitPrice = sale.getUnit_price();
        this.saleDate = sale.getSale_date();
    }
}


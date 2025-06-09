package com.tpfinal.stockmanager.model.implementations;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table (name = "product_sale")
public class ProductSale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Positive(message = "Quantity must be greater than 0")
    private int quantity;

    @Positive(message = "Unit price must greater than 0")
    private float unit_price;

    private LocalDateTime sale_date;

    @PrePersist
    public void prePersist() {
        sale_date = LocalDateTime.now();
    }
}


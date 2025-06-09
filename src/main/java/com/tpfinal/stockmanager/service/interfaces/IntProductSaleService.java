package com.tpfinal.stockmanager.service.interfaces;

import com.tpfinal.stockmanager.model.implementations.ProductSale;
import com.tpfinal.stockmanager.model.implementations.ProductSaleDTO;

import java.util.List;

public interface IntProductSaleService extends BaseService<ProductSale, Integer> {
    List<ProductSaleDTO> findAllDTO();
}

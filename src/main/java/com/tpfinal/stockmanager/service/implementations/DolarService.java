package com.tpfinal.stockmanager.service.implementations;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Service
public class DolarService {

    private final RestTemplate restTemplate = new RestTemplate();

    public BigDecimal obtenerCotizacionDolarOficial() {
        String url = "https://dolarapi.com/v1/dolares/oficial";
        ResponseEntity<DolarResponse> response = restTemplate.getForEntity(url, DolarResponse.class);
        return response.getBody().getVenta();
    }

    public static class DolarResponse {
        private BigDecimal compra;
        private BigDecimal venta;

        public BigDecimal getCompra() { return compra; }
        public void setCompra(BigDecimal compra) { this.compra = compra; }

        public BigDecimal getVenta() { return venta; }
        public void setVenta(BigDecimal venta) { this.venta = venta; }
    }
}
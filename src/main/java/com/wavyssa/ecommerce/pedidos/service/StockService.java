package com.wavyssa.ecommerce.pedidos.service;

import com.wavyssa.ecommerce.pedidos.model.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {

    @Autowired()
    private ProductoSevice productoService;

    @Scheduled(fixedRate = 1_0000)
    public void verificarStock() {

        try {
            List<Producto> productos = productoService.listarProductos();
            System.out.println("Estado del stock:");
            productos.forEach(producto -> {
                String disponibilidad;
                if (producto.getStock() == 0) {
                    disponibilidad = producto.getNombre() + " - FUERA DE STOCK";
                } else if (producto.getStock() <= 5) {
                    disponibilidad = producto.getNombre() + " - STOCK BAJO";
                } else {
                    disponibilidad = producto.getNombre() + " - STOCK ALTO";
                }
                System.out.println(disponibilidad);
            });

        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}

package com.wavyssa.ecommerce.pedidos.service;

import com.wavyssa.ecommerce.pedidos.model.Venta;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@Service
public class VentaService {

    private final List<Venta> historial_ventas = Collections.synchronizedList(new CopyOnWriteArrayList<>());

    public void registrarVenta(String producto, int cantidad, double total) {
        historial_ventas.add(new Venta(producto, cantidad, total));
    }

    @Scheduled(fixedRate = 3_0000)
    public void generarResumenVentas() {
        try {
            double ingresosTotales = historial_ventas.stream().mapToDouble(Venta::getTotal).sum();
            Map<String, Long> productosMasVendidos = historial_ventas.stream()
                    .collect(Collectors.groupingBy(Venta::getProducto, Collectors.summingLong(Venta::getCantidad)));
            System.out.println("Ingresos: " + ingresosTotales + " $ " + ". Productos más vendidos: " + productosMasVendidos);
        } catch (RuntimeException e) {
            System.out.println("Error al generar resumenVentas: " + e.getMessage()); // se podría cambiar a un log de registro
        }
    }
}

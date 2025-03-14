package com.wavyssa.ecommerce.pedidos.service;

import com.wavyssa.ecommerce.pedidos.exception.ApiServiceException;
import com.wavyssa.ecommerce.pedidos.model.Pedido;
import com.wavyssa.ecommerce.pedidos.model.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private ProductoSevice productoService;

    @Autowired
    private VentaService ventaService;

    public boolean procesarPedido(Pedido pedido) {
        boolean procesado = Boolean.FALSE;
        try {
            boolean pedidoExitoso = pedido.getProductos().entrySet().stream()
                    .allMatch(entry -> productoService.actualizarStock(entry.getKey(), entry.getValue()));

            if (pedidoExitoso) {
                pedido.getProductos().forEach((nombre_producto, cantidad) -> {
                    Optional<Producto> productoOptional = productoService.obtenerProducto(nombre_producto);
                    if (productoOptional.isPresent()) {
                        double precio = productoOptional.get().getPrecio();
                        ventaService.registrarVenta(nombre_producto, cantidad, (precio * cantidad));
                    }
                });
                procesado = Boolean.TRUE;
            }
            return procesado;
        } catch (RuntimeException e) {
            throw new ApiServiceException(e.getMessage(), e);
        }
    }
}

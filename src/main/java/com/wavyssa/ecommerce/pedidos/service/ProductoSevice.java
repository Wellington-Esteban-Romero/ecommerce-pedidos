package com.wavyssa.ecommerce.pedidos.service;

import com.wavyssa.ecommerce.pedidos.model.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoSevice {
    List<Producto> listarProductos();
    Producto agregarProducto(Producto producto);
    Producto actualizarProducto(Producto producto);
    Optional<Producto> obtenerProducto(String nombre);
    boolean actualizarStock(String nombre, int cantidad);
    void eliminarProducto(String nombre);
}

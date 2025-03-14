package com.wavyssa.ecommerce.pedidos.service;

import com.wavyssa.ecommerce.pedidos.exception.ApiServiceException;
import com.wavyssa.ecommerce.pedidos.model.Producto;
import com.wavyssa.ecommerce.pedidos.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoSevice {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<Producto> listarProductos() {
        return productoRepository.listar();
    }

    @Override
    public Producto agregarProducto(Producto producto) throws ApiServiceException {
        return productoRepository.agregar(producto);
    }

    @Override
    public Optional<Producto> obtenerProducto(String nombre) {
        return productoRepository.porNombre(nombre);
    }

    @Override
    public synchronized boolean actualizarStock(String nombre, int cantidad) {
        Optional<Producto> productoOptional = obtenerProducto(nombre);
        if (productoOptional.isPresent()) {
            Producto producto = productoOptional.get();
            if (producto.getStock() >= cantidad) {
                producto.setStock(producto.getStock() - cantidad);
                productoRepository.actualizar(producto);
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }
}

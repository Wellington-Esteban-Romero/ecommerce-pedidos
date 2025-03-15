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
        try {
            return productoRepository.listar();
        } catch (RuntimeException e) {
            throw new ApiServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Producto agregarProducto(Producto producto) {
        try {
            return productoRepository.agregar(producto);
        } catch (RuntimeException e) {
            throw new ApiServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Producto actualizarProducto(Producto producto) {
        try {
            return productoRepository.agregar(producto);
        } catch (RuntimeException e) {
            throw new ApiServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<Producto> obtenerProducto(String nombre) {
        try {
            return productoRepository.porNombre(nombre);
        } catch (RuntimeException e) {
            throw new ApiServiceException(e.getMessage(), e);
        }
    }

    @Override
    public synchronized boolean actualizarStock(String nombre, int cantidad) {
        try {
            Optional<Producto> productoOptional = obtenerProducto(nombre);
            if (productoOptional.isPresent()) {
                Producto producto = productoOptional.get();
                if (producto.getStock() >= cantidad) {
                    producto.setStock(producto.getStock() - cantidad);
                    actualizarProducto(producto);
                    return Boolean.TRUE;
                }
            }
        } catch (RuntimeException e) {
            throw new ApiServiceException(e.getMessage(), e);
        }
        return Boolean.FALSE;
    }

    @Override
    public void eliminarProducto(String nombre) {
        try {
            productoRepository.eliminar(nombre);
        } catch (RuntimeException e) {
            throw new ApiServiceException(e.getMessage(), e);
        }
    }
}

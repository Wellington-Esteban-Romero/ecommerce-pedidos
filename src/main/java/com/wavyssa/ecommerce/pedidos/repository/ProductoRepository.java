package com.wavyssa.ecommerce.pedidos.repository;

import com.wavyssa.ecommerce.pedidos.model.Producto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ProductoRepository {

    private final ConcurrentHashMap<String, Producto> productos = new ConcurrentHashMap<>();

    public ProductoRepository() {
        //productos de prueba
        productos.put("iMac", new Producto("iMac", 1500, 10));
        productos.put("Nintendo Switch", new Producto("Nintendo Switch", 300, 5));
        productos.put("Monitor ASUS", new Producto("Monitor ASUS", 400, 20));
    }

    public List<Producto> listar() throws RuntimeException {
        try {
            return List.copyOf(productos.values());
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public Optional<Producto> porNombre(String nombre) throws RuntimeException {
        try {
            return Optional.ofNullable(productos.get(nombre));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public Producto agregar(Producto producto) throws RuntimeException {
        try {
            productos.put(producto.getNombre(), producto);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return productos.get(producto.getNombre());
    }

    public void eliminar(String nombre) throws RuntimeException {
        try {
            productos.values().removeIf(prod -> prod.getNombre().equals(nombre));
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}

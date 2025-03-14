package com.wavyssa.ecommerce.pedidos.repository;

import com.wavyssa.ecommerce.pedidos.exception.ApiServiceException;
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
        productos.put("IMac", new Producto("IMac", 1500, 10));
        productos.put("Nintendo Switch", new Producto("Nintendo Switch", 300, 5));
        productos.put("Monitor ASUS", new Producto("Monitor ASUS", 400, 20));
    }

    public List<Producto> listar() {
        return List.copyOf(productos.values());
    }

    public Optional<Producto> porNombre(String nombre) {
        return Optional.ofNullable(productos.get(nombre));
    }

    public Producto agregar(Producto producto) throws ApiServiceException {
        if (!productos.containsKey(producto.getNombre())) {
            productos.put(producto.getNombre(), producto);
        } else {
            Optional<Producto> productoOptional = productos.values().stream()
                    .filter(producto1 -> producto1.getNombre().equals(producto.getNombre()))
                    .findAny();

            if (productoOptional.isPresent()) {
                Producto pro = productoOptional.get();
                pro.setStock(pro.getStock() + producto.getStock());
                productos.put(producto.getNombre(), pro);
            }
        }
        return productos.get(producto.getNombre());
    }

    public void actualizar(Producto producto) {
        productos.put(producto.getNombre(), producto);
    }
}

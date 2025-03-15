package com.wavyssa.ecommerce.pedidos.controller;

import com.wavyssa.ecommerce.pedidos.exception.ApiServiceException;
import com.wavyssa.ecommerce.pedidos.model.Pedido;
import com.wavyssa.ecommerce.pedidos.model.Producto;
import com.wavyssa.ecommerce.pedidos.service.PedidoService;
import com.wavyssa.ecommerce.pedidos.service.ProductoSevice;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private ProductoSevice productoService;

    @Autowired
    private PedidoService pedidoService;

    @GetMapping("/productos")
    public List<Producto> obtenerProductos() {
        return productoService.listarProductos();
    }

    @PostMapping("/producto")
    public ResponseEntity<?> agregarProducto(@Valid @RequestBody Producto producto_obj, BindingResult result) {

        Producto producto;
        Map<String, Object> response = new HashMap<>();

        ResponseEntity<Map<String, Object>> responseEntity = getMapResponseEntity(result, response);
        if (responseEntity != null) return responseEntity;

        try {
            producto = productoService.agregarProducto(producto_obj);
        } catch (ApiServiceException e) {
            response.put("mensaje", "Error al crear el producto");
            response.put("error", e.getMessage().concat(":").concat(e.getCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("success", "El producto se ha creado con éxito!");
        response.put("producto", producto);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/producto/{nombre}")
    public ResponseEntity<?> actualizarProducto(@Valid @RequestBody Producto producto_obj, @PathVariable String nombre, BindingResult result) {

        Optional<Producto> productoOptional;
        Producto producto;

        Map<String, Object> response = new HashMap<>();
        ResponseEntity<Map<String, Object>> responseEntity = getMapResponseEntity(result, response);
        if (responseEntity != null) return responseEntity;

        try {
            productoOptional = productoService.obtenerProducto(nombre);
        } catch (ApiServiceException e) {
            response.put("mensaje", "Error al obtener el producto: ".concat(nombre));
            response.put("error", e.getMessage().concat(":").concat(e.getCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (productoOptional.isEmpty()) {
            response.put("mensaje", "El producto actual con nombre: ".concat(nombre).concat(" no existe, no se puede actualizar"));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        try {
            producto = productoOptional.get();
            producto.setNombre(producto_obj.getNombre());
            producto.setPrecio(producto_obj.getPrecio());
            producto.setStock(producto_obj.getStock());
            producto = productoService.actualizarProducto(producto);
        } catch (ApiServiceException e) {
            response.put("mensaje", "Error al actualizar el producto");
            response.put("error", e.getMessage().concat(":").concat(e.getCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("success", "El producto se ha actualizado con éxito!");
        response.put("producto", producto);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/producto/{nombre}")
    public ResponseEntity<?> eliminarProducto(@PathVariable String nombre) {
        Map<String, Object> response = new HashMap<>();

        Optional<Producto> productoOptional;

        try {
            productoOptional = productoService.obtenerProducto(nombre);
        } catch (ApiServiceException e) {
            response.put("mensaje", "Error al eliminar el producto: ".concat(nombre));
            response.put("error", e.getMessage().concat(":").concat(e.getCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            if (productoOptional.isPresent()) {
                productoService.eliminarProducto(productoOptional.get().getNombre());
            } else {
                response.put("mensaje", "El producto: ".concat(nombre).concat(" no existe, no se puede eliminar"));
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (ApiServiceException e) {
            response.put("mensaje", "Error al eliminar el producto: ".concat(nombre));
            response.put("error", e.getMessage().concat(":").concat(e.getCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("success", "El producto se ha eliminado con éxito!");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/pedido")
    public ResponseEntity<?> crearPedido(@Valid @RequestBody Pedido pedido, BindingResult result) {

        boolean procesado;
        Map<String, Object> response = new HashMap<>();

        ResponseEntity<Map<String, Object>> responseEntity = getMapResponseEntity(result, response);
        if (responseEntity != null) return responseEntity;

        try {
            procesado = pedidoService.procesarPedido(pedido);
        } catch (ApiServiceException e) {
            response.put("mensaje", "Error al procesar el pedido");
            response.put("error", e.getMessage().concat(":").concat(e.getCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("success", procesado ? "El pedido se ha procesado con éxito!" : "El pedido no se ha podido procesar con éxito!");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private static ResponseEntity<Map<String, Object>> getMapResponseEntity(BindingResult result, Map<String, Object> response) {
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(error -> "El campo " + error.getField() + " contiene el error, " + error.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errors", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return null;
    }
}

package com.wavyssa.ecommerce.pedidos.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedHashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {

    @NotEmpty(message = "La lista de pedidos no puede estar vacía")
    private LinkedHashMap<
            @NotBlank(message = "El nombre del producto no puede ser vacío")
                    String,
            @NotNull(message = "La cantidad del producto no puede ser vacío")
            @Min(value = 1, message = "La cantidad debe ser al menos 1")
                    Integer> productos; // <nombre, cantidad> nombre del producto, cantidad que se va a pedir de un producto
}

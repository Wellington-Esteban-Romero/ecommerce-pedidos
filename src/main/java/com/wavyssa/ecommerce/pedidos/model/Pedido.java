package com.wavyssa.ecommerce.pedidos.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedHashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {

    @NotNull(message = "La lista de productos no puede ser null")
    private LinkedHashMap<
            @NotBlank(message = "El nombre del producto no puede ser vacío")
                    String,
            @NotBlank(message = "La cantidad del producto no puede ser vacío")
            @Positive(message = "La cantidad debe ser al menos 1")
                    Integer> productos; // <nombre, cantidad> nombre del producto, cantidad que se va a pedir de un producto
}

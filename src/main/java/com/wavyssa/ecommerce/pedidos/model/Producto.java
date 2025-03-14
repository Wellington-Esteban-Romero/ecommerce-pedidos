package com.wavyssa.ecommerce.pedidos.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Producto {

    @NotBlank(message = "El nombre no puede ser vacío")
    private String nombre;

    @NotNull(message = "El precio es obligatoria")
    @Min(value = 0, message = "El precio no puede ser negativo")
    private double precio;

    @NotNull(message = "El stock es obligatoria")
    @Min(value = 0, message = "El stock no puede ser negativo")
    private int stock;
}

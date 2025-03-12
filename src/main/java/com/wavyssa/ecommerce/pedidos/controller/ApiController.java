package com.wavyssa.ecommerce.pedidos.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping("/mensaje")
    public String mostrarMensaje() {
        return "Hola Mundo";
    }
}

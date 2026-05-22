package org.empresaabc.salesmanagement.modules.ventas.command.controller;

import org.empresaabc.salesmanagement.modules.ventas.command.dto.VentaRequestDTO;

import org.empresaabc.salesmanagement.modules.ventas.command.entity.Venta;

import org.empresaabc.salesmanagement.modules.ventas.command.service.VentaCommandService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(
        origins =
                "http://localhost:5173"
)
@RequestMapping("/ventas")
public class VentaCommandController {

    private final VentaCommandService ventaCommandService;

    public VentaCommandController(
            VentaCommandService ventaCommandService
    ) {
        this.ventaCommandService =
                ventaCommandService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Venta registrarVenta(
            @RequestBody
            VentaRequestDTO request
    ) {

        return ventaCommandService
                .registrarVenta(request);
    }
}
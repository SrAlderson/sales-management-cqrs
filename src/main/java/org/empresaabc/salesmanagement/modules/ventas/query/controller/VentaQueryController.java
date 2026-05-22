package org.empresaabc.salesmanagement.modules.ventas.query.controller;

import lombok.RequiredArgsConstructor;
import org.empresaabc.salesmanagement.modules.ventas.query.document.VentaDocument;
import org.empresaabc.salesmanagement.modules.ventas.query.service.VentaQueryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(
        origins =
                "http://localhost:5173"
)
@RequestMapping("/query/ventas")
@RequiredArgsConstructor
public class VentaQueryController {

    // ==========================================
    // DEPENDENCIA SERVICE
    // ==========================================

    private final VentaQueryService
            ventaQueryService;


    // ==========================================
    // CONSULTAR TODAS LAS VENTAS
    // ==========================================

    @GetMapping
    public List<VentaDocument>
    obtenerVentas() {

        return ventaQueryService
                .obtenerVentas();
    }


    // ==========================================
    // CONSULTAR POR CODIGO
    // ==========================================

    @GetMapping("/{codigoVenta}")
    public VentaDocument
    obtenerVentaPorCodigo(
            @PathVariable
            String codigoVenta
    ) {

        return ventaQueryService
                .obtenerVentaPorCodigo(
                        codigoVenta
                );
    }
}
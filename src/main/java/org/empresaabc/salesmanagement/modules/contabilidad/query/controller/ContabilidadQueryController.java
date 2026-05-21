package org.empresaabc.salesmanagement.modules.contabilidad.query.controller;

import lombok.RequiredArgsConstructor;
import org.empresaabc.salesmanagement.modules.contabilidad.query.document.MovimientoContableDocument;
import org.empresaabc.salesmanagement.modules.contabilidad.query.service.ContabilidadQueryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(
        "/query/contabilidad"
)
@RequiredArgsConstructor
public class ContabilidadQueryController {

    // ==========================================
    // DEPENDENCIA SERVICE
    // ==========================================

    private final
    ContabilidadQueryService
            contabilidadQueryService;


    // ==========================================
    // CONSULTAR TODOS
    // ==========================================

    @GetMapping
    public List<MovimientoContableDocument>
    obtenerTodosMovimientos() {

        return
                contabilidadQueryService
                        .obtenerTodosMovimientos();
    }


    // ==========================================
    // CONSULTAR POR CODIGO MOVIMIENTO
    // ==========================================

    @GetMapping(
            "/{codigoMovimiento}"
    )
    public MovimientoContableDocument
    obtenerPorCodigoMovimiento(
            @PathVariable
            String codigoMovimiento
    ) {

        return
                contabilidadQueryService
                        .obtenerPorCodigoMovimiento(
                                codigoMovimiento
                        );
    }


    // ==========================================
    // CONSULTAR POR CODIGO VENTA
    // ==========================================

    @GetMapping(
            "/venta/{codigoVenta}"
    )
    public MovimientoContableDocument
    obtenerPorCodigoVenta(
            @PathVariable
            String codigoVenta
    ) {

        return
                contabilidadQueryService
                        .obtenerPorCodigoVenta(
                                codigoVenta
                        );
    }
}
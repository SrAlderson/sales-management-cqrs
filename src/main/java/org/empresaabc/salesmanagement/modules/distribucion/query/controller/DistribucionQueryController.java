package org.empresaabc.salesmanagement.modules.distribucion.query.controller;

import lombok.RequiredArgsConstructor;
import org.empresaabc.salesmanagement.modules.distribucion.query.document.OrdenDespachoDocument;
import org.empresaabc.salesmanagement.modules.distribucion.query.service.DistribucionQueryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(
        origins =
                "http://localhost:5173"
)
@RequestMapping(
        "/query/distribucion"
)
@RequiredArgsConstructor
public class DistribucionQueryController {

    // ==========================================
    // DEPENDENCIA SERVICE
    // ==========================================

    private final
    DistribucionQueryService
            distribucionQueryService;


    // ==========================================
    // CONSULTAR TODOS
    // ==========================================

    @GetMapping
    public List<OrdenDespachoDocument>
    obtenerTodosDespachos() {

        return
                distribucionQueryService
                        .obtenerTodosDespachos();
    }


    // ==========================================
    // CONSULTAR POR CODIGO DESPACHO
    // ==========================================

    @GetMapping(
            "/{codigoDespacho}"
    )
    public OrdenDespachoDocument
    obtenerPorCodigoDespacho(
            @PathVariable
            String codigoDespacho
    ) {

        return
                distribucionQueryService
                        .obtenerPorCodigoDespacho(
                                codigoDespacho
                        );
    }


    // ==========================================
    // CONSULTAR POR CODIGO VENTA
    // ==========================================

    @GetMapping(
            "/venta/{codigoVenta}"
    )
    public OrdenDespachoDocument
    obtenerPorCodigoVenta(
            @PathVariable
            String codigoVenta
    ) {

        return
                distribucionQueryService
                        .obtenerPorCodigoVenta(
                                codigoVenta
                        );
    }
}
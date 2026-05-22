package org.empresaabc.salesmanagement.modules.facturacion.query.controller;

import lombok.RequiredArgsConstructor;
import org.empresaabc.salesmanagement.modules.facturacion.query.document.FacturaDocument;
import org.empresaabc.salesmanagement.modules.facturacion.query.service.FacturacionQueryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(
        origins =
                "http://localhost:5173"
)
@RequestMapping(
        "/query/facturacion"
)
@RequiredArgsConstructor
public class FacturacionQueryController {

    // ==========================================
    // DEPENDENCIA SERVICE
    // ==========================================

    private final
    FacturacionQueryService
            facturacionQueryService;


    // ==========================================
    // CONSULTAR TODAS
    // ==========================================

    @GetMapping
    public List<FacturaDocument>
    obtenerTodasFacturas() {

        return
                facturacionQueryService
                        .obtenerTodasFacturas();
    }


    // ==========================================
    // CONSULTAR POR NUMERO FACTURA
    // ==========================================

    @GetMapping(
            "/{numeroFactura}"
    )
    public FacturaDocument
    obtenerPorNumeroFactura(
            @PathVariable
            String numeroFactura
    ) {

        return
                facturacionQueryService
                        .obtenerPorNumeroFactura(
                                numeroFactura
                        );
    }


    // ==========================================
    // CONSULTAR POR CODIGO VENTA
    // ==========================================

    @GetMapping(
            "/venta/{codigoVenta}"
    )
    public FacturaDocument
    obtenerPorCodigoVenta(
            @PathVariable
            String codigoVenta
    ) {

        return
                facturacionQueryService
                        .obtenerPorCodigoVenta(
                                codigoVenta
                        );
    }
}
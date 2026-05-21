package org.empresaabc.salesmanagement.modules.facturacion.query.service;

import lombok.RequiredArgsConstructor;
import org.empresaabc.salesmanagement.modules.facturacion.query.document.FacturaDocument;
import org.empresaabc.salesmanagement.modules.facturacion.query.repository.FacturacionQueryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FacturacionQueryService {

    // ==========================================
    // DEPENDENCIA REPOSITORY
    // ==========================================

    private final
    FacturacionQueryRepository
            facturacionQueryRepository;


    // ==========================================
    // CONSULTAR TODAS LAS FACTURAS
    // ==========================================

    public List<FacturaDocument>
    obtenerTodasFacturas() {

        return
                facturacionQueryRepository
                        .findAll();
    }


    // ==========================================
    // CONSULTAR POR NUMERO FACTURA
    // ==========================================

    public FacturaDocument
    obtenerPorNumeroFactura(
            String numeroFactura
    ) {

        return
                facturacionQueryRepository
                        .findByNumeroFactura(
                                numeroFactura
                        )
                        .orElseThrow(
                                () ->
                                        new RuntimeException(
                                                "Factura no encontrada: "
                                                        + numeroFactura
                                        )
                        );
    }


    // ==========================================
    // CONSULTAR POR CODIGO VENTA
    // ==========================================

    public FacturaDocument
    obtenerPorCodigoVenta(
            String codigoVenta
    ) {

        return
                facturacionQueryRepository
                        .findByCodigoVenta(
                                codigoVenta
                        )
                        .orElseThrow(
                                () ->
                                        new RuntimeException(
                                                "Factura no encontrada para venta: "
                                                        + codigoVenta
                                        )
                        );
    }
}
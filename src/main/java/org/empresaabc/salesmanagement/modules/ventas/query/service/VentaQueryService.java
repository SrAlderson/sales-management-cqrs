package org.empresaabc.salesmanagement.modules.ventas.query.service;

import lombok.RequiredArgsConstructor;
import org.empresaabc.salesmanagement.modules.ventas.query.document.VentaDocument;
import org.empresaabc.salesmanagement.modules.ventas.query.repository.VentaQueryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VentaQueryService {

    // ==========================================
    // DEPENDENCIA REPOSITORY MONGO
    // ==========================================

    private final VentaQueryRepository
            ventaQueryRepository;


    // ==========================================
    // CONSULTAR TODAS LAS VENTAS
    // ==========================================

    public List<VentaDocument>
    obtenerVentas() {

        return ventaQueryRepository
                .findAll();
    }


    // ==========================================
    // CONSULTAR POR CODIGO
    // ==========================================

    public VentaDocument
    obtenerVentaPorCodigo(
            String codigoVenta
    ) {

        return ventaQueryRepository
                .findByCodigoVenta(
                        codigoVenta
                )
                .orElseThrow(
                        () ->
                                new RuntimeException(
                                        "Venta no encontrada"
                                )
                );
    }
}
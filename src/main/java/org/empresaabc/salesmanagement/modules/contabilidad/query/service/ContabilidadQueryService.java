package org.empresaabc.salesmanagement.modules.contabilidad.query.service;

import lombok.RequiredArgsConstructor;
import org.empresaabc.salesmanagement.modules.contabilidad.query.document.MovimientoContableDocument;
import org.empresaabc.salesmanagement.modules.contabilidad.query.repository.ContabilidadQueryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContabilidadQueryService {

    // ==========================================
    // DEPENDENCIA REPOSITORY
    // ==========================================

    private final
    ContabilidadQueryRepository
            contabilidadQueryRepository;


    // ==========================================
    // CONSULTAR TODOS LOS MOVIMIENTOS
    // ==========================================

    public List<MovimientoContableDocument>
    obtenerTodosMovimientos() {

        return
                contabilidadQueryRepository
                        .findAll();
    }


    // ==========================================
    // CONSULTAR POR CODIGO MOVIMIENTO
    // ==========================================

    public MovimientoContableDocument
    obtenerPorCodigoMovimiento(
            String codigoMovimiento
    ) {

        return
                contabilidadQueryRepository
                        .findByCodigoMovimiento(
                                codigoMovimiento
                        )
                        .orElseThrow(
                                () ->
                                        new RuntimeException(
                                                "Movimiento no encontrado: "
                                                        + codigoMovimiento
                                        )
                        );
    }


    // ==========================================
    // CONSULTAR POR CODIGO VENTA
    // ==========================================

    public MovimientoContableDocument
    obtenerPorCodigoVenta(
            String codigoVenta
    ) {

        return
                contabilidadQueryRepository
                        .findByCodigoVenta(
                                codigoVenta
                        )
                        .orElseThrow(
                                () ->
                                        new RuntimeException(
                                                "Movimiento no encontrado para venta: "
                                                        + codigoVenta
                                        )
                        );
    }
}
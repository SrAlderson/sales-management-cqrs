package org.empresaabc.salesmanagement.modules.distribucion.query.service;

import lombok.RequiredArgsConstructor;
import org.empresaabc.salesmanagement.modules.distribucion.query.document.OrdenDespachoDocument;
import org.empresaabc.salesmanagement.modules.distribucion.query.repository.DistribucionQueryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DistribucionQueryService {

    // ==========================================
    // DEPENDENCIA REPOSITORY
    // ==========================================

    private final
    DistribucionQueryRepository
            distribucionQueryRepository;


    // ==========================================
    // CONSULTAR TODOS
    // ==========================================

    public List<OrdenDespachoDocument>
    obtenerTodosDespachos() {

        return
                distribucionQueryRepository
                        .findAll();
    }


    // ==========================================
    // CONSULTAR POR CODIGO DESPACHO
    // ==========================================

    public OrdenDespachoDocument
    obtenerPorCodigoDespacho(
            String codigoDespacho
    ) {

        return
                distribucionQueryRepository
                        .findByCodigoDespacho(
                                codigoDespacho
                        )
                        .orElseThrow(
                                () ->
                                        new RuntimeException(
                                                "Despacho no encontrado: "
                                                        + codigoDespacho
                                        )
                        );
    }


    // ==========================================
    // CONSULTAR POR CODIGO VENTA
    // ==========================================

    public OrdenDespachoDocument
    obtenerPorCodigoVenta(
            String codigoVenta
    ) {

        return
                distribucionQueryRepository
                        .findByCodigoVenta(
                                codigoVenta
                        )
                        .orElseThrow(
                                () ->
                                        new RuntimeException(
                                                "Despacho no encontrado para venta: "
                                                        + codigoVenta
                                        )
                        );
    }
}
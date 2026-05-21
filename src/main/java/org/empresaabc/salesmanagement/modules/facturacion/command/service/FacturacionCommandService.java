package org.empresaabc.salesmanagement.modules.facturacion.command.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.empresaabc.salesmanagement.modules.facturacion.command.entity.Factura;
import org.empresaabc.salesmanagement.modules.facturacion.command.repository.FacturaRepository;
import org.empresaabc.salesmanagement.modules.facturacion.query.document.FacturaDocument;
import org.empresaabc.salesmanagement.modules.facturacion.query.repository.FacturacionQueryRepository;
import org.empresaabc.salesmanagement.modules.ventas.event.VentaCreadaEvent;
import org.empresaabc.salesmanagement.shared.enums.EstadoFactura;
import org.empresaabc.salesmanagement.shared.enums.TipoFactura;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class FacturacionCommandService {

    // ==========================================
    // DEPENDENCIAS
    // ==========================================

    private final
    FacturaRepository
            facturaRepository;

    private final
    FacturacionQueryRepository
            facturacionQueryRepository;


    // ==========================================
    // GENERAR FACTURA
    // ==========================================

    public void generarFactura(
            VentaCreadaEvent
                    ventaEvent
    ) {

        String numeroFactura =
                generarNumeroFactura();

        LocalDateTime fechaFactura =
                LocalDateTime.now();

        String cliente =
                ventaEvent
                        .getPrimerNombre()
                        + " "
                        + ventaEvent
                        .getPrimerApellido();


        // ==========================================
        // CREAR ENTITY POSTGRES
        // ==========================================

        Factura factura =
                new Factura();

        factura.setNumeroFactura(
                numeroFactura
        );

        factura.setCodigoVenta(
                ventaEvent
                        .getCodigoVenta()
        );

        factura.setCliente(
                cliente
        );

        factura.setCorreoCliente(
                ventaEvent
                        .getCorreoCliente()
        );

        factura.setFechaFactura(
                fechaFactura
        );

        factura.setTipoFactura(
                ventaEvent
                        .getTipoFactura()
        );

        factura.setTotalFactura(
                ventaEvent
                        .getTotalVenta()
        );


        // ==========================================
        // REGLA NEGOCIO FACTURACION
        // ==========================================

        EstadoFactura
                estadoFactura;

        if (
                ventaEvent
                        .getTipoFactura()
                        ==
                        TipoFactura
                                .ELECTRONICA
        ) {

            estadoFactura =
                    EstadoFactura
                            .ENVIADA;

            log.info(
                    "Factura electrónica enviada para venta: {} al correo {}",
                    ventaEvent
                            .getCodigoVenta(),
                    ventaEvent
                            .getCorreoCliente()
            );

        } else {

            estadoFactura =
                    EstadoFactura
                            .PENDIENTE_IMPRESION;

            log.info(
                    "Factura general creada para impresión: {}",
                    ventaEvent
                            .getCodigoVenta()
            );
        }

        factura.setEstadoFactura(
                estadoFactura
        );


        // ==========================================
        // GUARDAR POSTGRES
        // ==========================================

        facturaRepository
                .save(
                        factura
                );


        // ==========================================
        // CREAR DOCUMENT MONGO
        // ==========================================

        FacturaDocument
                document =
                FacturaDocument
                        .builder()
                        .numeroFactura(
                                numeroFactura
                        )
                        .codigoVenta(
                                ventaEvent
                                        .getCodigoVenta()
                        )
                        .fechaFactura(
                                fechaFactura
                        )
                        .cliente(
                                cliente
                        )
                        .correoCliente(
                                ventaEvent
                                        .getCorreoCliente()
                        )
                        .tipoFactura(
                                ventaEvent
                                        .getTipoFactura()
                        )
                        .estadoFactura(
                                estadoFactura
                        )
                        .totalFactura(
                                ventaEvent
                                        .getTotalVenta()
                        )
                        .build();


        // ==========================================
        // GUARDAR MONGO
        // ==========================================

        facturacionQueryRepository
                .save(
                        document
                );

        log.info(
                "Factura generada correctamente: {}",
                numeroFactura
        );
    }


    // ==========================================
    // GENERAR NUMERO FACTURA
    // ==========================================

    private String
    generarNumeroFactura() {

        return "FAC-"
                + UUID.randomUUID()
                .toString()
                .substring(
                        0,
                        8
                )
                .toUpperCase();
    }
}
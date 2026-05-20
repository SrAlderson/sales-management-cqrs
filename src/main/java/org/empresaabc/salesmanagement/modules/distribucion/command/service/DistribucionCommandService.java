package org.empresaabc.salesmanagement.modules.distribucion.command.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.empresaabc.salesmanagement.modules.distribucion.command.entity.OrdenDespacho;
import org.empresaabc.salesmanagement.modules.distribucion.command.repository.OrdenDespachoRepository;
import org.empresaabc.salesmanagement.modules.distribucion.query.document.OrdenDespachoDocument;
import org.empresaabc.salesmanagement.modules.distribucion.query.repository.DistribucionQueryRepository;
import org.empresaabc.salesmanagement.modules.ventas.event.VentaCreadaEvent;
import org.empresaabc.salesmanagement.shared.enums.EstadoDespacho;
import org.empresaabc.salesmanagement.shared.enums.TipoEntrega;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class DistribucionCommandService {

    // ==========================================
    // DEPENDENCIAS
    // ==========================================

    private final
    OrdenDespachoRepository
            ordenDespachoRepository;

    private final
    DistribucionQueryRepository
            distribucionQueryRepository;


    // ==========================================
    // CREAR ORDEN DESPACHO
    // ==========================================

    public void crearOrdenDespacho(
            VentaCreadaEvent
                    ventaEvent
    ) {

        // ==========================================
        // REGLA NEGOCIO
        // SOLO DOMICILIO
        // ==========================================

        if (
                ventaEvent
                        .getTipoEntrega()
                        != TipoEntrega.DOMICILIO
        ) {

            log.info(
                    "Venta {} no requiere distribución",
                    ventaEvent
                            .getCodigoVenta()
            );

            return;
        }

        // ==========================================
        // CREAR ENTITY POSTGRES
        // ==========================================

        OrdenDespacho
                ordenDespacho =
                new OrdenDespacho();

        String
                codigoDespacho =
                generarCodigoDespacho();

        ordenDespacho
                .setCodigoDespacho(
                        codigoDespacho
                );

        ordenDespacho
                .setCodigoVenta(
                        ventaEvent
                                .getCodigoVenta()
                );

        ordenDespacho
                .setCliente(
                        ventaEvent
                                .getPrimerNombre()
                                + " "
                                + ventaEvent
                                .getPrimerApellido()
                );

        ordenDespacho
                .setTelefonoCliente(
                        ventaEvent
                                .getTelefonoCliente()
                );

        ordenDespacho
                .setDireccionEntrega(
                        ventaEvent
                                .getDireccionEntrega()
                );

        ordenDespacho
                .setCiudad(
                        ventaEvent
                                .getCiudad()
                );

        ordenDespacho
                .setFechaCreacion(
                        LocalDateTime.now()
                );

        ordenDespacho
                .setEstadoDespacho(
                        EstadoDespacho
                                .PENDIENTE_PREPARACION
                );


        // ==========================================
        // GUARDAR POSTGRES
        // ==========================================

        ordenDespachoRepository
                .save(
                        ordenDespacho
                );


        // ==========================================
        // CREAR DOCUMENT MONGO
        // ==========================================

        OrdenDespachoDocument
                document =
                OrdenDespachoDocument
                        .builder()
                        .codigoDespacho(
                                codigoDespacho
                        )
                        .codigoVenta(
                                ventaEvent
                                        .getCodigoVenta()
                        )
                        .cliente(
                                ventaEvent
                                        .getPrimerNombre()
                                        + " "
                                        + ventaEvent
                                        .getPrimerApellido()
                        )
                        .telefonoCliente(
                                ventaEvent
                                        .getTelefonoCliente()
                        )
                        .direccionEntrega(
                                ventaEvent
                                        .getDireccionEntrega()
                        )
                        .ciudad(
                                ventaEvent
                                        .getCiudad()
                        )
                        .fechaCreacion(
                                LocalDateTime.now()
                        )
                        .estadoDespacho(
                                EstadoDespacho
                                        .PENDIENTE_PREPARACION
                        )
                        .build();


        // ==========================================
        // GUARDAR MONGO
        // ==========================================

        distribucionQueryRepository
                .save(
                        document
                );

        log.info(
                "Orden despacho creada: {}",
                codigoDespacho
        );
    }


    // ==========================================
    // GENERAR CODIGO DESPACHO
    // ==========================================

    private String
    generarCodigoDespacho() {

        return "DSP-"
                + UUID.randomUUID()
                .toString()
                .substring(
                        0,
                        8
                )
                .toUpperCase();
    }
}
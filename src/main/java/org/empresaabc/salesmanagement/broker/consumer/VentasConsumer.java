package org.empresaabc.salesmanagement.broker.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.empresaabc.salesmanagement.broker.event.DetalleVentaEvent;
import org.empresaabc.salesmanagement.broker.event.VentaCreadaEvent;
import org.empresaabc.salesmanagement.config.RabbitConfig;
import org.empresaabc.salesmanagement.query.document.DetalleVentaDocument;
import org.empresaabc.salesmanagement.query.document.VentaDocument;
import org.empresaabc.salesmanagement.query.repository.VentaQueryRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class VentasConsumer {

    // ==========================================
    // DEPENDENCIA MONGO
    // ==========================================

    private final VentaQueryRepository
            ventaQueryRepository;


    // ==========================================
    // CONSUMER
    // ==========================================

    @RabbitListener(
            queues =
                    RabbitConfig.VENTAS_QUEUE
    )
    public void consumirVenta(
            VentaCreadaEvent event
    ) {

        // ==========================================
        // MAP EVENT → DOCUMENT
        // ==========================================

        List<DetalleVentaDocument>
                detallesDocument =

                event.getDetalles()
                        .stream()
                        .map(
                                this::mapDetalle
                        )
                        .toList();


        VentaDocument ventaDocument =
                VentaDocument
                        .builder()
                        .codigoVenta(
                                event.getCodigoVenta()
                        )
                        .fechaVenta(
                                event.getFechaVenta()
                        )
                        .vendedor(
                                event.getVendedor()
                        )
                        .estadoVenta(
                                event.getEstadoVenta()
                        )
                        .tipoEntrega(
                                event.getTipoEntrega()
                        )
                        .tipoFactura(
                                event.getTipoFactura()
                        )
                        .totalVenta(
                                event.getTotalVenta()
                        )
                        .primerNombre(
                                event.getPrimerNombre()
                        )
                        .segundoNombre(
                                event.getSegundoNombre()
                        )
                        .primerApellido(
                                event.getPrimerApellido()
                        )
                        .segundoApellido(
                                event.getSegundoApellido()
                        )
                        .tipoDocumento(
                                event.getTipoDocumento()
                        )
                        .numeroDocumento(
                                event.getNumeroDocumento()
                        )
                        .correoCliente(
                                event.getCorreoCliente()
                        )
                        .telefonoCliente(
                                event.getTelefonoCliente()
                        )
                        .direccionEntrega(
                                event.getDireccionEntrega()
                        )
                        .ciudad(
                                event.getCiudad()
                        )
                        .detalles(
                                detallesDocument
                        )
                        .build();


        // ==========================================
        // GUARDAR EN MONGO
        // ==========================================

        ventaQueryRepository.save(
                ventaDocument
        );


        // ==========================================
        // LOG
        // ==========================================

        log.info(
                "===================================="
        );

        log.info(
                "VENTA GUARDADA EN MONGO"
        );

        log.info(
                "Código venta: {}",
                event.getCodigoVenta()
        );

        log.info(
                "Cliente: {} {}",
                event.getPrimerNombre(),
                event.getPrimerApellido()
        );

        log.info(
                "===================================="
        );
    }


    // ==========================================
    // MAP DETALLE
    // ==========================================

    private DetalleVentaDocument
    mapDetalle(
            DetalleVentaEvent detalle
    ) {

        return DetalleVentaDocument
                .builder()
                .nombreArticulo(
                        detalle.getNombreArticulo()
                )
                .cantidad(
                        detalle.getCantidad()
                )
                .precioUnitario(
                        detalle.getPrecioUnitario()
                )
                .subtotal(
                        detalle.getSubtotal()
                )
                .build();
    }
}
package org.empresaabc.salesmanagement.modules.contabilidad.command.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.empresaabc.salesmanagement.modules.contabilidad.command.entity.MovimientoContable;
import org.empresaabc.salesmanagement.modules.contabilidad.command.repository.MovimientoContableRepository;
import org.empresaabc.salesmanagement.modules.contabilidad.query.document.MovimientoContableDocument;
import org.empresaabc.salesmanagement.modules.contabilidad.query.repository.ContabilidadQueryRepository;
import org.empresaabc.salesmanagement.modules.ventas.event.VentaCreadaEvent;
import org.empresaabc.salesmanagement.shared.enums.EstadoMovimiento;
import org.empresaabc.salesmanagement.shared.enums.TipoMovimiento;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ContabilidadCommandService {

    // ==========================================
    // DEPENDENCIAS
    // ==========================================

    private final
    MovimientoContableRepository
            movimientoContableRepository;

    private final
    ContabilidadQueryRepository
            contabilidadQueryRepository;


    // ==========================================
    // REGISTRAR MOVIMIENTO
    // ==========================================

    public void registrarMovimiento(
            VentaCreadaEvent
                    ventaEvent
    ) {

        String codigoMovimiento =
                generarCodigoMovimiento();

        LocalDateTime fechaMovimiento =
                LocalDateTime.now();

        String cliente =
                ventaEvent
                        .getPrimerNombre()
                        + " "
                        + ventaEvent
                        .getPrimerApellido();


        // ==========================================
        // ENTITY POSTGRES
        // ==========================================

        MovimientoContable
                movimiento =
                new MovimientoContable();

        movimiento
                .setCodigoMovimiento(
                        codigoMovimiento
                );

        movimiento
                .setCodigoVenta(
                        ventaEvent
                                .getCodigoVenta()
                );

        movimiento
                .setFechaMovimiento(
                        fechaMovimiento
                );

        movimiento
                .setTipoMovimiento(
                        TipoMovimiento
                                .INGRESO_VENTA
                );

        movimiento
                .setEstadoMovimiento(
                        EstadoMovimiento
                                .REGISTRADO
                );

        movimiento
                .setValorMovimiento(
                        ventaEvent
                                .getTotalVenta()
                );

        movimiento
                .setCliente(
                        cliente
                );


        // ==========================================
        // GUARDAR POSTGRES
        // ==========================================

        movimientoContableRepository
                .save(
                        movimiento
                );


        // ==========================================
        // DOCUMENT MONGO
        // ==========================================

        MovimientoContableDocument
                document =
                MovimientoContableDocument
                        .builder()
                        .codigoMovimiento(
                                codigoMovimiento
                        )
                        .codigoVenta(
                                ventaEvent
                                        .getCodigoVenta()
                        )
                        .fechaMovimiento(
                                fechaMovimiento
                        )
                        .tipoMovimiento(
                                TipoMovimiento
                                        .INGRESO_VENTA
                        )
                        .estadoMovimiento(
                                EstadoMovimiento
                                        .REGISTRADO
                        )
                        .valorMovimiento(
                                ventaEvent
                                        .getTotalVenta()
                        )
                        .cliente(
                                cliente
                        )
                        .build();


        // ==========================================
        // GUARDAR MONGO
        // ==========================================

        contabilidadQueryRepository
                .save(
                        document
                );

        log.info(
                "Movimiento contable registrado: {}",
                codigoMovimiento
        );
    }


    // ==========================================
    // GENERAR CODIGO MOVIMIENTO
    // ==========================================

    private String
    generarCodigoMovimiento() {

        return "MOV-"
                + UUID.randomUUID()
                .toString()
                .substring(
                        0,
                        8
                )
                .toUpperCase();
    }
}
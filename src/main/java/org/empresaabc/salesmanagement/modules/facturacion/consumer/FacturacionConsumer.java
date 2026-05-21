package org.empresaabc.salesmanagement.modules.facturacion.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.empresaabc.salesmanagement.config.RabbitConfig;
import org.empresaabc.salesmanagement.modules.facturacion.command.service.FacturacionCommandService;
import org.empresaabc.salesmanagement.modules.ventas.event.VentaCreadaEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class FacturacionConsumer {

    // ==========================================
    // DEPENDENCIA SERVICE
    // ==========================================

    private final
    FacturacionCommandService
            facturacionCommandService;


    // ==========================================
    // ESCUCHAR EVENTO DE VENTA
    // ==========================================

    @RabbitListener(
            queues =
                    RabbitConfig
                            .FACTURACION_QUEUE
    )
    public void consumirVenta(
            VentaCreadaEvent
                    ventaEvent
    ) {

        log.info(
                "Evento recibido en facturación: {}",
                ventaEvent
                        .getCodigoVenta()
        );

        facturacionCommandService
                .generarFactura(
                        ventaEvent
                );
    }
}
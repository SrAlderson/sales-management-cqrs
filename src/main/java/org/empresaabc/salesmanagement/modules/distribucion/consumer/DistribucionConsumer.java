package org.empresaabc.salesmanagement.modules.distribucion.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.empresaabc.salesmanagement.config.RabbitConfig;
import org.empresaabc.salesmanagement.modules.distribucion.command.service.DistribucionCommandService;
import org.empresaabc.salesmanagement.modules.ventas.event.VentaCreadaEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DistribucionConsumer {

    // ==========================================
    // DEPENDENCIA SERVICE
    // ==========================================

    private final
    DistribucionCommandService
            distribucionCommandService;


    // ==========================================
    // ESCUCHAR EVENTO DE VENTA
    // ==========================================

    @RabbitListener(
            queues =
                    RabbitConfig
                            .DISTRIBUCION_QUEUE
    )
    public void consumirVenta(
            VentaCreadaEvent
                    ventaEvent
    ) {

        log.info(
                "Evento recibido en distribución: {}",
                ventaEvent
                        .getCodigoVenta()
        );

        distribucionCommandService
                .crearOrdenDespacho(
                        ventaEvent
                );
    }
}
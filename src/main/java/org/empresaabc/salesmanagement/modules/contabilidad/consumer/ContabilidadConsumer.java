package org.empresaabc.salesmanagement.modules.contabilidad.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.empresaabc.salesmanagement.config.RabbitConfig;
import org.empresaabc.salesmanagement.modules.contabilidad.command.service.ContabilidadCommandService;
import org.empresaabc.salesmanagement.modules.ventas.event.VentaCreadaEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ContabilidadConsumer {

    // ==========================================
    // DEPENDENCIA SERVICE
    // ==========================================

    private final
    ContabilidadCommandService
            contabilidadCommandService;


    // ==========================================
    // ESCUCHAR EVENTO DE VENTA
    // ==========================================

    @RabbitListener(
            queues =
                    RabbitConfig
                            .CONTABILIDAD_QUEUE
    )
    public void consumirVenta(
            VentaCreadaEvent
                    ventaEvent
    ) {

        log.info(
                "Evento recibido en contabilidad: {}",
                ventaEvent
                        .getCodigoVenta()
        );

        contabilidadCommandService
                .registrarMovimiento(
                        ventaEvent
                );
    }
}
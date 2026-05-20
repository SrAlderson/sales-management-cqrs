package org.empresaabc.salesmanagement.broker.consumer;

import lombok.extern.slf4j.Slf4j;
import org.empresaabc.salesmanagement.broker.event.VentaCreadaEvent;
import org.empresaabc.salesmanagement.config.RabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ContabilidadConsumer {

    @RabbitListener(
            queues =
                    RabbitConfig.CONTABILIDAD_QUEUE
    )
    public void procesarVentaContabilidad(
            VentaCreadaEvent event
    ) {

        log.info(
                "===================================="
        );

        log.info(
                "MODULO CONTABILIDAD"
        );

        log.info(
                "Registrando ingreso contable"
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
                "Valor ingreso: {}",
                event.getTotalVenta()
        );

        log.info(
                "===================================="
        );
    }
}
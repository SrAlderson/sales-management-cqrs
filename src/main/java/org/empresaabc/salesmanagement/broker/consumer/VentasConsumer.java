package org.empresaabc.salesmanagement.broker.consumer;

import lombok.extern.slf4j.Slf4j;
import org.empresaabc.salesmanagement.broker.event.VentaCreadaEvent;
import org.empresaabc.salesmanagement.config.RabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class VentasConsumer {

    @RabbitListener(
            queues =
                    RabbitConfig.VENTAS_QUEUE
    )
    public void consumirVenta(
            VentaCreadaEvent event
    ) {

        log.info(
                "===================================="
        );

        log.info(
                "VENTA RECIBIDA EN MODULO VENTAS"
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
                "Total venta: {}",
                event.getTotalVenta()
        );

        log.info(
                "Cantidad artículos: {}",
                event.getDetalles()
                        .size()
        );

        log.info(
                "===================================="
        );
    }
}
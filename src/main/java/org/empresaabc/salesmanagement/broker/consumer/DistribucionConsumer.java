package org.empresaabc.salesmanagement.broker.consumer;

import lombok.extern.slf4j.Slf4j;
import org.empresaabc.salesmanagement.broker.event.VentaCreadaEvent;
import org.empresaabc.salesmanagement.config.RabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DistribucionConsumer {

    @RabbitListener(
            queues =
                    RabbitConfig.DISTRIBUCION_QUEUE
    )
    public void procesarDistribucion(
            VentaCreadaEvent event
    ) {

        log.info(
                "===================================="
        );

        log.info(
                "MODULO DISTRIBUCION"
        );

        log.info(
                "Creando orden de despacho"
        );

        log.info(
                "Código venta: {}",
                event.getCodigoVenta()
        );

        log.info(
                "Dirección entrega: {}",
                event.getDireccionEntrega()
        );

        log.info(
                "Ciudad destino: {}",
                event.getCiudad()
        );

        log.info(
                "Cantidad productos: {}",
                event.getDetalles()
                        .size()
        );

        log.info(
                "===================================="
        );
    }
}
package org.empresaabc.salesmanagement.broker.consumer;

import lombok.extern.slf4j.Slf4j;
import org.empresaabc.salesmanagement.modules.ventas.event.VentaCreadaEvent;
import org.empresaabc.salesmanagement.config.RabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FacturacionConsumer {

    @RabbitListener(
            queues =
                    RabbitConfig.FACTURACION_QUEUE
    )
    public void procesarFacturacion(
            VentaCreadaEvent event
    ) {

        log.info(
                "===================================="
        );

        log.info(
                "MODULO FACTURACION"
        );

        log.info(
                "Generando factura"
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
                "Tipo factura: {}",
                event.getTipoFactura()
        );

        log.info(
                "Total facturar: {}",
                event.getTotalVenta()
        );

        log.info(
                "===================================="
        );
    }
}
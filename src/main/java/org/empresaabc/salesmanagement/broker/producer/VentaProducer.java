package org.empresaabc.salesmanagement.broker.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.empresaabc.salesmanagement.broker.event.VentaCreadaEvent;
import org.empresaabc.salesmanagement.config.RabbitConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class VentaProducer {

    private final RabbitTemplate rabbitTemplate;

    public void enviarVentaCreada(
            VentaCreadaEvent event
    ) {

        log.info(
                "Enviando evento de venta a RabbitMQ. Código venta: {}",
                event.getCodigoVenta()
        );

        rabbitTemplate.convertAndSend(
                RabbitConfig.SALES_EXCHANGE,
                RabbitConfig.SALES_ROUTING_KEY,
                event
        );

        log.info(
                "Evento enviado correctamente a RabbitMQ"
        );
    }
}
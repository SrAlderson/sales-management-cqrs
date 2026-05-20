package org.empresaabc.salesmanagement.config;

import jakarta.annotation.PostConstruct;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    // ==================================================
    // EXCHANGE
    // ==================================================

    public static final String SALES_EXCHANGE =
            "sales.exchange";


    // ==================================================
    // ROUTING KEY
    // ==================================================

    public static final String SALES_ROUTING_KEY =
            "sales.created";


    // ==================================================
    // QUEUES
    // ==================================================

    public static final String VENTAS_QUEUE =
            "ventas.queue";

    public static final String CONTABILIDAD_QUEUE =
            "contabilidad.queue";

    public static final String DISTRIBUCION_QUEUE =
            "distribucion.queue";

    public static final String FACTURACION_QUEUE =
            "facturacion.queue";


    // ==================================================
    // VALIDACION STARTUP
    // ==================================================

    @PostConstruct
    public void init() {

        System.out.println(
                "RabbitMQ configurado correctamente"
        );
    }


    // ==================================================
    // EXCHANGE
    // ==================================================

    @Bean
    public TopicExchange salesExchange() {

        return ExchangeBuilder
                .topicExchange(
                        SALES_EXCHANGE
                )
                .durable(true)
                .build();
    }


    // ==================================================
    // QUEUES
    // ==================================================

    @Bean
    public Queue ventasQueue() {

        return QueueBuilder
                .durable(
                        VENTAS_QUEUE
                )
                .build();
    }

    @Bean
    public Queue contabilidadQueue() {

        return QueueBuilder
                .durable(
                        CONTABILIDAD_QUEUE
                )
                .build();
    }

    @Bean
    public Queue distribucionQueue() {

        return QueueBuilder
                .durable(
                        DISTRIBUCION_QUEUE
                )
                .build();
    }

    @Bean
    public Queue facturacionQueue() {

        return QueueBuilder
                .durable(
                        FACTURACION_QUEUE
                )
                .build();
    }


    // ==================================================
    // BINDINGS
    // ==================================================

    @Bean
    public Binding ventasBinding() {

        return BindingBuilder
                .bind(
                        ventasQueue()
                )
                .to(
                        salesExchange()
                )
                .with(
                        SALES_ROUTING_KEY
                );
    }

    @Bean
    public Binding contabilidadBinding() {

        return BindingBuilder
                .bind(
                        contabilidadQueue()
                )
                .to(
                        salesExchange()
                )
                .with(
                        SALES_ROUTING_KEY
                );
    }

    @Bean
    public Binding distribucionBinding() {

        return BindingBuilder
                .bind(
                        distribucionQueue()
                )
                .to(
                        salesExchange()
                )
                .with(
                        SALES_ROUTING_KEY
                );
    }

    @Bean
    public Binding facturacionBinding() {

        return BindingBuilder
                .bind(
                        facturacionQueue()
                )
                .to(
                        salesExchange()
                )
                .with(
                        SALES_ROUTING_KEY
                );
    }


    // ==================================================
    // RABBIT ADMIN
    // ==================================================

    @Bean
    public RabbitAdmin rabbitAdmin(
            ConnectionFactory connectionFactory
    ) {

        return new RabbitAdmin(
                connectionFactory
        );
    }


    // ==================================================
    // JSON CONVERTER
    // ==================================================

    @Bean
    public MessageConverter jsonMessageConverter() {

        return new Jackson2JsonMessageConverter();
    }


    // ==================================================
    // RABBIT TEMPLATE
    // ==================================================

    @Bean
    public RabbitTemplate rabbitTemplate(
            ConnectionFactory connectionFactory
    ) {

        RabbitTemplate rabbitTemplate =
                new RabbitTemplate(
                        connectionFactory
                );

        rabbitTemplate.setMessageConverter(
                jsonMessageConverter()
        );

        return rabbitTemplate;
    }


    // ==================================================
    // CREACION MANUAL DE TOPOLOGY
    // ==================================================

    @Bean
    public CommandLineRunner rabbitInitializer(
            RabbitAdmin rabbitAdmin
    ) {

        return args -> {

            // Exchange
            rabbitAdmin.declareExchange(
                    salesExchange()
            );

            // Queues
            rabbitAdmin.declareQueue(
                    ventasQueue()
            );

            rabbitAdmin.declareQueue(
                    contabilidadQueue()
            );

            rabbitAdmin.declareQueue(
                    distribucionQueue()
            );

            rabbitAdmin.declareQueue(
                    facturacionQueue()
            );

            // Bindings
            rabbitAdmin.declareBinding(
                    ventasBinding()
            );

            rabbitAdmin.declareBinding(
                    contabilidadBinding()
            );

            rabbitAdmin.declareBinding(
                    distribucionBinding()
            );

            rabbitAdmin.declareBinding(
                    facturacionBinding()
            );

            System.out.println(
                    "RabbitMQ queues creadas correctamente"
            );
        };
    }
}
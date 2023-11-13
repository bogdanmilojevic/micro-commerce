package com.microcommerce.orderservice.config;

import com.microcommerce.orderservice.data.event.OrderCreatedEvent;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.UUIDSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Configuration
public class KafkaProducerConfiguration {

//    TODO: User environment variables
//    @Value("${KAFKA_BROKER_BOOTSTRAP_SERVER}")
//    private String kafkaBrokerBootstrapServerUrl;

    @Bean
    public ProducerFactory<UUID, OrderCreatedEvent> producerFactory() {
        Map<String, Object> properties = new HashMap<>(){{
           put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
           put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, UUIDSerializer.class);
           put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        }};

        return new DefaultKafkaProducerFactory<>(properties);
    }

    @Bean
    public KafkaTemplate<UUID, OrderCreatedEvent> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}

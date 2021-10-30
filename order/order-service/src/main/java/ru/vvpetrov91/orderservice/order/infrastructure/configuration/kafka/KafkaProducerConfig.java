package ru.vvpetrov91.orderservice.order.infrastructure.configuration.kafka;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.serializer.JsonSerializer;
import ru.vvpetrov91.orderservice.api.event.OrderEvent;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(KafkaConfigProperties.class)
public class KafkaProducerConfig {
    private final KafkaConfigProperties kafkaConfigProperties;

    @Bean
    public Map<String, Object> producerProperties() {
        Map<String, Object> producerProperties = new HashMap<>();

        producerProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaConfigProperties.getServer());
        producerProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        producerProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return producerProperties;
    }

    @Bean
    public DefaultKafkaProducerFactory<String, OrderEvent> orderProducerFactory() {
        return new DefaultKafkaProducerFactory<>(producerProperties());
    }

    @Bean
    public KafkaTemplate<String, OrderEvent> orderKafkaTemplate() {
        return new KafkaTemplate<>(orderProducerFactory());
    }
}
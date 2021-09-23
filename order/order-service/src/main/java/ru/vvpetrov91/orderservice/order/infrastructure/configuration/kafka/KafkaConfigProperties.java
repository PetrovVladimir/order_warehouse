package ru.vvpetrov91.orderservice.order.infrastructure.configuration.kafka;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("kafka")
public class KafkaConfigProperties {
    private String server;
    private String groupId;
}
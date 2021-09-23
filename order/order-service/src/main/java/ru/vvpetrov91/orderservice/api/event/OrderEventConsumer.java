package ru.vvpetrov91.orderservice.api.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.vvpetrov91.orderservice.order.infrastructure.OrderEventHandler;

import static ru.vvpetrov91.orderservice.order.infrastructure.KafkaOrderNotificationService.ORDER_TOPIC;

@Slf4j
@Service
@EnableKafka
@RequiredArgsConstructor
public class OrderEventConsumer {
    private final OrderEventHandler orderEventHandler;

    @KafkaListener(topics = ORDER_TOPIC)
    public void handleOrderEvent(OrderEvent orderEvent) {
        log.info("Received order event {}", orderEvent);

        try {
            if (OrderEvent.EVENT_VERSION.equals(orderEvent.getEventVersion())) {
                orderEventHandler.handle(orderEvent);
                log.info("Order event was successfully processed {}", orderEvent);
            } else log.info("Incorrect order event version {}", orderEvent);
        } catch (Exception e) {
            log.error("Event handing error: {}", orderEvent, e);
        }
    }
}
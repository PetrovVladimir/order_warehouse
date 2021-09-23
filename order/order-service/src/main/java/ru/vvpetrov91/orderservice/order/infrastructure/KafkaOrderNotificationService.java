package ru.vvpetrov91.orderservice.order.infrastructure;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import ru.vvpetrov91.common.notification.NotificationService;
import ru.vvpetrov91.common.notification.NotificationServiceException;
import ru.vvpetrov91.orderservice.api.event.OrderEvent;


@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaOrderNotificationService implements NotificationService<OrderEvent> {
    public static final String ORDER_TOPIC = "order-events";

    private final KafkaTemplate<String, OrderEvent> orderEventKafkaTemplate;

    @Override
    public void send(OrderEvent event) throws NotificationServiceException {
        try {
            ListenableFuture<SendResult<String, OrderEvent>> future = orderEventKafkaTemplate.send(
                    ORDER_TOPIC,
                    event.getEventEntityId(),
                    event
            );

            future.addCallback(
                    successResult -> log.info("Event sent successfully: {}", event),
                    failureResult -> log.error("Failed to send event: {}", event, failureResult)
            );
        } catch (Exception e) {
            throw new NotificationServiceException("Send event error", e);
        }
    }
}
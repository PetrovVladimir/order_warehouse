package ru.vvpetrov91.orderservice.order.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import ru.vvpetrov91.common.notification.NotificationService;
import ru.vvpetrov91.common.notification.NotificationServiceException;
import ru.vvpetrov91.orderservice.api.event.OrderEvent;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Component
@Slf4j
@RequiredArgsConstructor
public class OrderOperations {
    private final NotificationService<OrderEvent> notificationService;

    public String createOrderBy(String orderName) throws OrderOperationsException {
        if (StringUtils.isEmpty(orderName)) {
            log.error("Order name is empty");
            throw new OrderOperationsException("Order name is empty");
        }

        try {
            String orderId
                    = new UUID(ThreadLocalRandom.current().nextLong(), ThreadLocalRandom.current().nextLong()).toString();
            notificationService.send(Order.createBy(orderId, orderName));

            return orderId;
        } catch (NotificationServiceException e) {
            log.error("Failed to send event");
            throw new OrderOperationsException("Failed to send event");
        }
    }
}
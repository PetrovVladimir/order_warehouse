package ru.vvpetrov91.orderservice.order.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vvpetrov91.orderservice.api.event.OrderEvent;
import ru.vvpetrov91.orderservice.api.event.OrderEventPayload;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import static java.util.Comparator.comparing;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private String name;
    private String status;

    public static OrderEvent createBy(String orderId, String orderName) {
        return new OrderEvent(
                new UUID(ThreadLocalRandom.current().nextLong(), ThreadLocalRandom.current().nextLong()).toString(),
                "order_context",
                "no_author",
                "created",
                OrderEvent.EVENT_VERSION,
                LocalDateTime.ofInstant(Instant.now(), ZoneOffset.UTC),
                orderId,
                OrderEventPayload.builder()
                        .name(orderName)
                        .status("created")
                        .build()
        );
    }

    //метод использован исключительно для примера - обычно здесь "собирается" агрегат путем прохода по всем ивентам - стандартный метод apply
    // далее может быть сгенерировано следующее событие, логика и т.д.
    public static Optional<Order> findBy(List<OrderEvent> orderEvents) {
        final OrderEvent lastOrderEvent;

        if (!orderEvents.isEmpty()) {
            orderEvents.sort(comparing(OrderEvent::getEventOccurringTime));

             lastOrderEvent = orderEvents.get(orderEvents.size() - 1);
        } else {
            return Optional.empty();
        }

        final OrderEventPayload lastOrderEventPayload = lastOrderEvent.getEventPayload();

        return Optional.of(new Order(lastOrderEventPayload.getName(), lastOrderEventPayload.getStatus()));
    }
}
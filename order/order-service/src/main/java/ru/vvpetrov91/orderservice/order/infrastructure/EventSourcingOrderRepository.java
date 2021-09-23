package ru.vvpetrov91.orderservice.order.infrastructure;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.vvpetrov91.orderservice.order.domain.Order;
import ru.vvpetrov91.orderservice.order.domain.OrderEventJournal;
import ru.vvpetrov91.orderservice.order.domain.OrderRepository;


import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class EventSourcingOrderRepository implements OrderRepository {
    private final OrderEventJournal orderEventJournal;

    @Override
    public Optional<Order> findByEventEntityId(String orderId) {
        try {
            return Order.findBy(orderEventJournal.findByEventEntityId(orderId));
        } catch (Exception e) {
            log.error("Failed to find order events by id {}", orderId);
           return Optional.empty();
        }
    }
}
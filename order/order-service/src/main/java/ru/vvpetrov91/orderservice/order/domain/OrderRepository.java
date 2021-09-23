package ru.vvpetrov91.orderservice.order.domain;

import java.util.Optional;

public interface OrderRepository {
    Optional<Order> findByEventEntityId(String orderId);
}
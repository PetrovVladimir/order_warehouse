package ru.vvpetrov91.orderservice.order.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vvpetrov91.orderservice.api.event.OrderEvent;

import java.util.List;

public interface OrderEventJournal extends JpaRepository<OrderEvent, Long> {
    List<OrderEvent> findByEventEntityId(String parentId);
}
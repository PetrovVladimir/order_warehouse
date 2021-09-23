package ru.vvpetrov91.orderservice.order.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import ru.vvpetrov91.orderservice.api.event.OrderEvent;
import ru.vvpetrov91.orderservice.order.domain.OrderEventJournal;

@Service
@RequiredArgsConstructor
@EnableTransactionManagement
public class OrderEventHandler {
    private final OrderEventJournal orderEventJournal;

    @Transactional
    public void handle(OrderEvent orderEvent) {
        orderEventJournal.save(orderEvent);
    }
}
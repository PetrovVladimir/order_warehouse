package ru.vvpetrov91.orderservice.order.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.vvpetrov91.orderservice.order.domain.*;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderOperations orderOperations;

    public String createOrderBy(String orderName) throws OrderServiceException {

        try {
            return orderOperations.createOrderBy(orderName);
        } catch (OrderOperationsException e) {
            log.error("Failed to create order");
            throw new OrderServiceException(e.getMessage());
        }
    }

    public OrderDTO getOrderBy(String orderId) throws OrderServiceException {
        Optional<Order> orderOptional = orderRepository.findByEventEntityId(orderId);
        if (orderOptional.isEmpty()) {
            log.error("Failed to find order by id: {}", orderId);
            throw new OrderServiceException("Failed to find order by id");
        }

        final Order order = orderOptional.get();

        return new OrderDTO(orderId, order.getName(), order.getStatus());
    }
}
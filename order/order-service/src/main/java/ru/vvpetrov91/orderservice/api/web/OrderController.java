package ru.vvpetrov91.orderservice.api.web;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.vvpetrov91.orderservice.api.web.create.CreateOrderRequest;
import ru.vvpetrov91.orderservice.api.web.create.CreateOrderResponse;
import ru.vvpetrov91.orderservice.api.web.instance.OrderInstanceResponse;
import ru.vvpetrov91.orderservice.order.service.OrderDTO;
import ru.vvpetrov91.orderservice.order.service.OrderService;
import ru.vvpetrov91.orderservice.order.service.OrderServiceException;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @Operation(description = "Создать заказ")
    @PostMapping()
    public CreateOrderResponse createOrder(@RequestBody CreateOrderRequest createOrderRequest)
            throws OrderServiceException {

        return new CreateOrderResponse(orderService.createOrderBy(createOrderRequest.getOrderName()));
    }

    @Operation(description = "Посмотреть созданный заказ")
    @GetMapping()
    public OrderInstanceResponse getOrderBy(@RequestParam String orderId)
            throws OrderServiceException {

        final OrderDTO orderDTO = orderService.getOrderBy(orderId);

        return new OrderInstanceResponse(orderDTO);
    }
}
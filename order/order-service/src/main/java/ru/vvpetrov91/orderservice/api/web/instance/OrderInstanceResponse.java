package ru.vvpetrov91.orderservice.api.web.instance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vvpetrov91.orderservice.order.service.OrderDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderInstanceResponse {
    private OrderDTO orderDTO;
}
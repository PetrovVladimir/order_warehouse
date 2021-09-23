package ru.vvpetrov91.orderservice.order.service;

public class OrderServiceException extends Exception {
    public OrderServiceException(String message) {
        super(message);
    }

    public OrderServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
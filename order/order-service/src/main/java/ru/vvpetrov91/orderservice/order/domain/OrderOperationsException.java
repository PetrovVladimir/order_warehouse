package ru.vvpetrov91.orderservice.order.domain;

public class OrderOperationsException extends Exception {
    public OrderOperationsException(String message) {
        super(message);
    }
}
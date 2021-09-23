package ru.vvpetrov91.orderservice.api.web;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.vvpetrov91.orderservice.order.service.OrderServiceException;

@ControllerAdvice
public class PublicOrderServiceExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {OrderServiceException.class})
    protected ResponseEntity<String> handleConflict(Exception e) {

        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(String.format("{\"message\": \"%s\"}", e.getMessage()));
    }
}
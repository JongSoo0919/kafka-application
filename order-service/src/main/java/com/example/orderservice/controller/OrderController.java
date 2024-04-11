package com.example.orderservice.controller;

import com.example.orderservice.domain.Orders;
import com.example.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/order/detail")
    public List<Orders> getOrders() {
        return orderService.getOrders();
    }

    @GetMapping("/order/health-check")
    public ResponseEntity<String> getHealthCheck() {
        return ResponseEntity.status(HttpStatus.OK).body("order Success");
    }

    @GetMapping("/order/error")
    public ResponseEntity<String> getError() {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("order Error");
    }
}

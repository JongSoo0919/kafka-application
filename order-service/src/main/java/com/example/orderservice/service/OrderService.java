package com.example.orderservice.service;

import com.example.orderservice.domain.Orders;
import com.example.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    @KafkaListener(topics = "buy")
    public Orders createOrders(String data) throws ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(data);

        return orderRepository.save(
                Orders.builder()
                        .productId((Long) jsonObject.get("product_id"))
                        .quantity((Long) jsonObject.get("quantity"))
                        .build()
        );
    }
    public Long getCount() {
        return orderRepository.count();
    }

    public Orders getOrdersByOrderId(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("주문 번호가 존재하지 않습니다."));
    }

    public List<Orders> getOrders(){
        return orderRepository.findAll();
    }
}

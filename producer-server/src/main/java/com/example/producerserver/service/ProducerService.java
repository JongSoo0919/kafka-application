package com.example.producerserver.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class ProducerService {
    private final KafkaTemplate<String, Object> template;


    public void sendMessageToTopic(String message) throws JsonProcessingException {
        Map<String, Object> map = new HashMap<>();
        map.put("product_id", 1L);
        map.put("quantity", 100);
        map.put("name", "jongsoo");
        ObjectMapper objectMapper = new ObjectMapper();

        CompletableFuture<SendResult<String, Object>> completableFuture = template.send("buy", objectMapper.writeValueAsString(map));
        completableFuture.whenComplete((result, ex) -> {
            if (ex == null) {
                System.out.println("Sent message = [" + message + " ] with offset=[" + result.getRecordMetadata().offset() + "]");
            }
        });
    }
}

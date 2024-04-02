package com.example.producerserver.controller;

import com.example.producerserver.service.ProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/kafka")
public class ProducerController {
    private final ProducerService producerService;
    @GetMapping("/topic/buy")
    public ResponseEntity<String> buy(String message) throws Exception{
        producerService.sendMessageToTopic(message);
        return ResponseEntity.ok("Producer Call Success!");
    }
}

package com.example.userservice.controller;

import com.example.userservice.domain.Users;
import com.example.userservice.service.UserService;
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
public class UserController {
    private final UserService userService;
    @GetMapping("/user/detail")
    public List<Users> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/user/health-check")
    public ResponseEntity<String> getHealthCheck() {
        return ResponseEntity.status(HttpStatus.OK).body("user Success");
    }
    @GetMapping("/user/error")
    public ResponseEntity<String> getErrors() {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("user Error");
    }
}

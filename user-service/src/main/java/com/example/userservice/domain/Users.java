package com.example.userservice.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Long productId;
    @Column(nullable = false)
    private Long orderId;
    @CreatedDate
    private LocalDateTime createdAt;

    @Builder
    public Users(String name, Long productId, Long orderId, LocalDateTime createdAt) {
        this.name = name;
        this.productId = productId;
        this.orderId = orderId;
        this.createdAt = createdAt == null ? LocalDateTime.now() : createdAt;
    }
}

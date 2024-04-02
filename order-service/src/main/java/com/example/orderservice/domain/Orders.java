package com.example.orderservice.domain;

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
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    @Column(nullable = false)
    private Long productId;
    @Column(nullable = false)
    private Long quantity;
    @CreatedDate
    private LocalDateTime createdAt;

    @Builder
    public Orders(Long productId, Long quantity, LocalDateTime createdAt) {
        this.productId = productId;
        this.quantity = quantity;
        this.createdAt = createdAt == null ? LocalDateTime.now() : createdAt;
    }
}

package com.example.productservice.service;

import com.example.productservice.domain.Products;
import com.example.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Products getProductByProductId(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("제품 번호가 존재하지 않습니다."));
    }

    @KafkaListener(topics = "buy")
    public Products updateProductTotalQuantity(String data) throws ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(data);

        Products products = getProductByProductId((Long) jsonObject.get("product_id"));
        products.updateQuantity((Long) jsonObject.get("quantity"));

        return productRepository.save(products);
    }

    public List<Products> getProducts() {
        return productRepository.findAll();
    }
}

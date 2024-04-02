package com.example.userservice.service;

import com.example.userservice.domain.Users;
import com.example.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public static Long orderId = 1L;
    public Long getCount() {
        return userRepository.count();
    }

    public Users getUsersByUserId(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("유저가 존재하지 않습니다."));
    }

    @KafkaListener(topics = "buy")
    public Users createUsers(String data) throws ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(data);

        return userRepository.save(
                Users.builder()
                        .name((String) jsonObject.get("name"))
                        .productId((Long) jsonObject.get("product_id"))
                        .orderId(orderId++)
                        .build()
        );
    }

    public List<Users> getUsers(){
        return userRepository.findAll();
    }
}

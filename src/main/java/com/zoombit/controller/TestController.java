package com.zoombit.controller;

import com.zoombit.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private RedisTemplate redisTemplate;

    @Qualifier("kafkaTemplate")
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @GetMapping("/")
    public String test() {
        testRepository.findAll().forEach(System.out::println);
        return "test";
    }

    @PostMapping("/set")
    public String setValue(@RequestParam String key, @RequestParam String value) {
        redisTemplate.opsForValue().set(key, value);
        return "Stored in Redis: " + key + " = " + value;
    }

    @GetMapping("/get")
    public String getValue(@RequestParam String key) {
        String value = (String) redisTemplate.opsForValue().get(key);
        return value != null ? "Fetched from Redis: " + key + " = " + value : "Key not found!";
    }

    @PostMapping("/send")
    public String sendMessage(@RequestParam String topic, @RequestParam String message) {
        kafkaTemplate.send(topic, message);
        return "Message sent to Kafka: " + message;
    }

}

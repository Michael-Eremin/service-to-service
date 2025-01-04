package com.example.stockService;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
public class OderConsumer {
    @Bean
    public Consumer<Order> input() {
        System.out.println("Input Order");
        return order -> {
            System.out.println("Received order: " + order.getName() + " from department " + order.getDepartment());
        };
    }
}

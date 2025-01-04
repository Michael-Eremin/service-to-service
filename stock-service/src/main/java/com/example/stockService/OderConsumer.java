package com.example.stockService;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
public class OderConsumer {
    @Bean
    public Consumer<com.example.stockService.Order> input() {
        System.out.println("Input Order");
        return order -> {
            String name = order.getName();
            String department = order.getDepartment();
            System.out.println("Received order: " + name + " from department " + department);
        };
    }
}

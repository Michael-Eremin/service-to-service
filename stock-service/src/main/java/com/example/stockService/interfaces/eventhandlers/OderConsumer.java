package com.example.stockService.interfaces.eventhandlers;

import com.example.stockService.domain.dto.Order;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
public class OderConsumer {
    @Bean
    public Consumer<Order> input() {
        System.out.println("Input Order");
        return order -> {
            String name = order.getName();
            String department = order.getDepartment();
            System.out.println("Received order: " + name + " from department " + department);
        };
    }
}

package com.example.stockService.interfaces.eventhandlers;

import com.example.stockService.domain.dto.Order;
import com.example.stockService.services.HttpTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
public class OrderConsumer {
    private final HttpTaskService httpTaskService;
    public OrderConsumer(@Autowired HttpTaskService httpTaskService) {
        this.httpTaskService = httpTaskService;
    }
    @Bean
    public Consumer<Order> input() {
        System.out.println("Input Order");
        return order -> {
            String name = order.getName();
            String department = order.getDepartment();
            System.out.println("Received order: " + name + " from department " + department);
            if (name != null && name.equals("pdf")) {
                System.out.println("toPdf()");
                httpTaskService.toPdf()
                        .doOnSuccess(v -> System.out.println("Request successfully sent"))
                        .doOnError(e -> System.err.println("Error: " + e.getMessage()))
                        .subscribe();
            }
        };
    }
}

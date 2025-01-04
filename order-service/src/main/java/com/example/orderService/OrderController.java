package com.example.orderService;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class OrderController {
    private final OrderProducer orderProducer;

    public OrderController(OrderProducer orderProducer) {
        this.orderProducer = orderProducer;
    }

    @PostMapping("/orders1")
    public String createEmployee1(@RequestBody Order order) {
        System.out.println("post");
        System.out.println("order:getName() " + order.getName());
        orderProducer.sendEmployee1(order);
        System.out.println("sent");
        return "Order sent: " + order.getName();
    }

    @PostMapping("/orders2")
    public String createEmployee2(@RequestBody Order order) {
        System.out.println("post");
        System.out.println("order:getName() " + order.getName());
        orderProducer.sendEmployee2(order);
        System.out.println("sent");
        return "Order sent: " + order.getName();
    }
}

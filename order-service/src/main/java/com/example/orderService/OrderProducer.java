package com.example.orderService;

import com.example.orderService.Order;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {
    private final StreamBridge streamBridge1;
    private final StreamBridge streamBridge2;

    public OrderProducer(
            StreamBridge streamBridge1,
            StreamBridge streamBridge2
                         ) {
        this.streamBridge1 = streamBridge1;
        this.streamBridge2 = streamBridge2;
    }

    public void sendEmployee1(Order order) {
        System.out.println("sent to output1");
        streamBridge1.send("output1", order);
    }

    public void sendEmployee2(Order order) {
        System.out.println("sent to output2");
        streamBridge2.send("output2", order);
    }
}

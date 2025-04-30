
package com.example.kafkafanout.controller;

import com.example.kafkafanout.model.Order;
import com.example.kafkafanout.producer.OrderProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderProducerService producerService;

    @PostMapping("/sendAll")
    public String sendOrders(@RequestBody List<Order> orders) {
        orders.forEach(producerService::sendOrder);
        return "Orders sent to Kafka!";
    }
}

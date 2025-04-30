
package com.example.kafkafanout.consumer;

import com.example.kafkafanout.model.Item;
import com.example.kafkafanout.model.Order;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumerService {

    @KafkaListener(topics = "track-orders", groupId = "billing-group", containerFactory = "orderKafkaListenerFactory")
    public void processBilling(Order order) {
        double total = order.getItems().stream().mapToDouble(Item::getPrice).sum();
        double totalWithGST = total * 1.18;
        System.out.println("Billing → Order " + order.getOrderId() + ": ₹" + totalWithGST + " (including GST)");
    }

    @KafkaListener(topics = "track-orders", groupId = "notification-group", containerFactory = "orderKafkaListenerFactory")
    public void processNotification(Order order) {
        System.out.println("Notification → Order " + order.getOrderId() + " placed by " + order.getCustomerName() + " is confirmed.");
    }

    @KafkaListener(topics = "track-orders", groupId = "delivery-group", containerFactory = "orderKafkaListenerFactory")
    public void processDelivery(Order order) {
        String location = order.getLocation();
        String estimate = "Standard".equalsIgnoreCase(order.getDeliveryType()) ? "3-5 days" : "1-2 days";
        System.out.println("Delivery → Order " + order.getOrderId() + " to " + location + " with estimated delivery: " + estimate);
    }
}

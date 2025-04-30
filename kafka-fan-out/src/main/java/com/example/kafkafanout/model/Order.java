
package com.example.kafkafanout.model;

import lombok.Data;

import java.util.List;

@Data
public class Order {
    private String orderId;
    private String customerName;
    private String location;
    private String deliveryType;
    private List<Item> items;

}

package com.example.kafkafanout;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class KafkaFanOutApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaFanOutApplication.class, args);
	}

}

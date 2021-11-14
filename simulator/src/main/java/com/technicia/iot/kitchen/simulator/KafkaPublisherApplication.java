package com.technicia.iot.kitchen.simulator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableScheduling
public class KafkaPublisherApplication {

	@Autowired
	private KafkaTemplate<String, Object> template;

	private String topic = "grocerystream";

	@GetMapping("/ping")
	public String ping() {
		return "pong";
	}

	@GetMapping("/testPublish")
	public String testPublish() {
		Message message = new Message(-1, 1, 1);
		template.send(topic, message);
		return "Json Data published";
	}

	@Scheduled(fixedDelay = 10000)
	public void sendRandomData() {
		Message message = new Message(2352, getGroceryId(1, 3), 1);
		template.send(topic, message);
	}

	public int getGroceryId(int min, int max) {
		return (int) ((Math.random() * (max - min)) + min);
	}

	public static void main(String[] args) {
		SpringApplication.run(KafkaPublisherApplication.class, args);
	}
}

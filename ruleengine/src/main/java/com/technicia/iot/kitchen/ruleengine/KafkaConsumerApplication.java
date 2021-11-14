package com.technicia.iot.kitchen.ruleengine;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.technicia.iot.kitchen.ruleengine.service.GroceryMessageService;
import com.technicia.iot.kitchen.ruleengine.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableScheduling
@SpringBootApplication
@RestController
public class KafkaConsumerApplication {

	List<Message> messages = new ArrayList<>();

	@Autowired
	GroceryMessageService groceryMessageService;

	@Autowired
	NotificationService notificationService;

	@GetMapping("/ping")
	public String ping() {
		return "pong";
	}

	@KafkaListener(groupId = "grocerystream-1", topics = {"grocerystream"}, containerFactory = "kafkaListenerContainerFactory")
	public List<Message> getMsgFromTopic(String data) {

		ObjectMapper objectMapper = new ObjectMapper();

		try {
			Message message = objectMapper.readValue(data, Message.class);
			messages.add(message);
			groceryMessageService.saveMessage(message);

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(data);
		return messages;
	}

	@Scheduled(fixedDelay = 10000)
	private void notifyCurrentStatus() {
		notificationService.checkAndNotifyGroceriesStatus();
	}

	public static void main(String[] args) {
		SpringApplication.run(KafkaConsumerApplication.class, args);
	}
}

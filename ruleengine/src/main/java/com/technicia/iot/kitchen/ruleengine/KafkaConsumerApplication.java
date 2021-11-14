package com.technicia.iot.kitchen.ruleengine;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class KafkaConsumerApplication {

	List<String> messages = new ArrayList<>();

	User userFromTopic = null;

	@KafkaListener(groupId = "grocerystream-1", topics = {"grocerystream"}, containerFactory = "kafkaListenerContainerFactory")
	public List<String> getMsgFromTopic(String data) {
		System.out.println(data);
		messages.add(data);
		return messages;
	}

//	@KafkaListener(groupId = "grocerystream-1", topics = {"grocerystream"}, containerFactory = "userKafkaListenerContainerFactory")
//	public User getJsonMsgFromTopic(User user) {
//		userFromTopic = user;
//		return userFromTopic;
//	}

	public static void main(String[] args) {
		SpringApplication.run(KafkaConsumerApplication.class, args);
	}
}

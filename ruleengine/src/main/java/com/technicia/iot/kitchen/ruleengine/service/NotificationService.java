package com.technicia.iot.kitchen.ruleengine.service;

import com.technicia.iot.kitchen.ruleengine.model.Grocery;
import com.technicia.iot.kitchen.ruleengine.model.Message;
import com.technicia.iot.kitchen.ruleengine.model.NotificationMessage;
import com.technicia.iot.kitchen.ruleengine.repository.GroceryRepository;
import com.technicia.iot.kitchen.ruleengine.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {

    @Autowired
    MessageRepository messageRepository;
    RestTemplate restTemplate = new RestTemplate();

    @Autowired
    GroceryRepository groceryRepository;

    public void checkAndNotifyGroceriesStatus() {

        List<NotificationMessage> notificationMessages = new ArrayList<>();
        List<Message> existingGroceries = messageRepository.findAll();

        existingGroceries.stream().forEach(message -> {
            Optional<Grocery> groceryDetail = groceryRepository.findById(message.getGroceryId());
            if(groceryDetail.isPresent()) {
                if (groceryDetail.get().getLimit() > message.getAvailableQuantity()) {
                    NotificationMessage notificationMessage = new NotificationMessage(groceryDetail.get().getName(),   "your stock seems to be low please check it out", "cLV8bpISTGOX6duO73ss3V:APA91bGR4iTWeDyt0X3cj-FYlXpvRHD_DvdfVeaZ340QX3enl_aga3F-Lr4shvsX2C7R-_-BVbCUmhwNdh7H_d7fm0EMLx9_yrUuxVg7WJn7VZ9o4hTGgsJtQjB7CuX-kDj_HHhQy309");
                    notificationMessages.add(notificationMessage);
                }
            }
        });

        if (notificationMessages.stream().count()> 0) {
            String url = "http://localhost:8080/api/v1/notification";
            ResponseEntity<NotificationMessage> response = restTemplate.postForEntity(url, notificationMessages.stream().findFirst().get(), NotificationMessage.class);
            System.out.println(response.toString());
        }
    }
}


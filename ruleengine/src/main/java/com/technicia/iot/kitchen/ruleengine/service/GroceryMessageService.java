package com.technicia.iot.kitchen.ruleengine.service;

import com.technicia.iot.kitchen.ruleengine.Message;
import com.technicia.iot.kitchen.ruleengine.model.Grocery;
import com.technicia.iot.kitchen.ruleengine.model.GroceryType;
import com.technicia.iot.kitchen.ruleengine.repository.GroceryRepository;
import com.technicia.iot.kitchen.ruleengine.repository.GroceryTypeRepository;
import com.technicia.iot.kitchen.ruleengine.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component("groceryMessageService")
public class GroceryMessageService {
    @Autowired
    MessageRepository messageRepository;

    @Autowired
    GroceryTypeRepository groceryTypeRepository;

    @Autowired
    GroceryRepository groceryRepository;

    public void saveMessage(Message message) {
        com.technicia.iot.kitchen.ruleengine.model.Message messageModel = new com.technicia.iot.kitchen.ruleengine.model.Message();
        messageModel.setAvailableQuantity(message.getQuantity());
        messageModel.setUserId(message.getUserId());
        messageModel.setGroceryId(message.getGroceryId());
        messageRepository.save(messageModel);
    }

    public void getGroceryType(String inputGroceryType){
        List<GroceryType> groceryTypeList = groceryTypeRepository.findAll();
        Optional<GroceryType> groceryType = groceryTypeList.stream().filter(type -> type.getType().equals(inputGroceryType)).findFirst();
    }

    public void getGrocery(){
        List<Grocery> groceryList = groceryRepository.findAll();
    }
}

package com.technicia.iot.kitchen.ruleengine.repository;

import com.technicia.iot.kitchen.ruleengine.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message,Integer> {

}
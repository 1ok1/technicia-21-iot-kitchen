package com.technicia.iot.kitchen.ruleengine.repository;

import com.technicia.iot.kitchen.ruleengine.model.Grocery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroceryRepository extends JpaRepository<Grocery,Integer> {

}
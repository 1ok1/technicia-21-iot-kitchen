package com.technicia.iot.kitchen.ruleengine.repository;

import com.technicia.iot.kitchen.ruleengine.model.GroceryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroceryTypeRepository extends JpaRepository<GroceryType,String> {

}
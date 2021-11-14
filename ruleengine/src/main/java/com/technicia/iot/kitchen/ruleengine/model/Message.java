package com.technicia.iot.kitchen.ruleengine.model;

import javax.persistence.*;

@Entity
@Table(name = "MESSAGE")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "GROCERY_ID")
    private int groceryId;

    @Column(name = "AVAILABLE_QUANTITY")
    private int availableQuantity;

    @Column(name = "USER_ID")
    private int userId;

    public Message() {
    }

    public Message(int id, int groceryId, int availableQuantity, int userId) {
        this.id = id;
        this.groceryId = groceryId;
        this.availableQuantity = availableQuantity;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGroceryId() {
        return groceryId;
    }

    public void setGroceryId(int groceryId) {
        this.groceryId = groceryId;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
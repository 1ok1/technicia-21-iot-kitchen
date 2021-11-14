package com.technicia.iot.kitchen.ruleengine.model;

import javax.persistence.*;

@Entity
@Table(name = "GROCERY_TYPE")
public class GroceryType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "UNIT")
    private int unit;

    public GroceryType() {
    }

    public GroceryType(Long id, String type, int unit) {
        this.id = id;
        this.type = type;
        this.unit = unit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }
}
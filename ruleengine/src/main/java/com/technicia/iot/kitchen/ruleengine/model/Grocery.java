package com.technicia.iot.kitchen.ruleengine.model;


import javax.persistence.*;

@Entity
@Table(name = "GROCERY")
public class Grocery {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "NAME")
	private String name;

	@ManyToOne
	@JoinColumn(name = "GROCERY_TYPE")
	private GroceryType type;

	@Column(name = "GROCERY_LIMIT")
	private int limit;

	public Grocery() {

	}

	public Grocery(int id, String name, GroceryType type, int limit) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.limit = limit;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public GroceryType getType() {
		return type;
	}

	public void setType(GroceryType type) {
		this.type = type;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}
}

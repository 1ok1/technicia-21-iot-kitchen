package com.technicia.iot.kitchen.simulator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
	private Integer userId;
	private Integer groceryId;
	private Integer quantity;

}

package com.zensar.olxmaster.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Master {

	private int id;
	private String title;
	private int price;
	private String category;
	private String description;
	private String username;
	private String createdDate;
	private String modifiedDate;
	private String status;
}
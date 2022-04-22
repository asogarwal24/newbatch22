package com.zensar.olxadvertise.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zensar.olxadvertise.entity.Category;
import com.zensar.olxadvertise.entity.Status;

@RestController
@RequestMapping("/advertise")
public class OlxAdvertiseController {

	static List<Category> categories = new ArrayList<Category>();  /*Advertisement categories means 'Real Estate', 'Furniture', 
	                                                                'Electronic goods', 'Vehicles' etc.*/

	static {
		categories.add(new Category(1, "Furniture"));
		categories.add(new Category(2, "Cars"));
		categories.add(new Category(3, "Mobiles"));
		categories.add(new Category(4, "Real-Estate"));
		categories.add(new Category(5, "Sports"));
	}

	static List<Status> statusList = new ArrayList<Status>();
	static {
		statusList.add(new Status(1, "OPEN"));

		statusList.add(new Status(2, "CLOSED"));
	}

	@GetMapping(value="/{category}",produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public List<Category> getAllCategories() {
		return categories;
	}

	@GetMapping(value="/{status}",produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public List<Status> getAllStatus() {
		return statusList;
	}

}

package com.zensar.olxmaster.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zensar.olxmaster.entity.Master;


@RestController
@RequestMapping(value="/advertise")
public class OlxMasterController {

	static List<Master> advertises = new ArrayList<Master>();

	static {
		advertises.add(new Master(1, "laptop sale", 54000, "Electronic goods", "intel core 3 sony vaio", "anand", "xxx",
				"xxx", "OPEN"));

	}

	@PostMapping(produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},consumes= {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Master> registerUser(@RequestBody Master advertise,
			@RequestHeader("auth-token") String token) {

		if (token.equals("abhi12345")) {
			advertises.add(advertise);
		} else {
			return new ResponseEntity<Master>(advertise, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Master>(advertise, HttpStatus.CREATED);

	}

	@PutMapping(value="/{id}",produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},consumes= {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public Master updateAdvertise(@PathVariable int id, @RequestBody Master advertise) {

		Master availableAdvertise = getMaster(id);
		availableAdvertise.setTitle(advertise.getTitle());
		availableAdvertise.setPrice(advertise.getPrice());
		availableAdvertise.setCategory(advertise.getCategory());
		availableAdvertise.setDescription(advertise.getDescription());
		availableAdvertise.setUsername(advertise.getUsername());
		availableAdvertise.setCreatedDate(advertise.getCreatedDate());
		availableAdvertise.setModifiedDate(advertise.getModifiedDate());
		availableAdvertise.setStatus(advertise.getStatus());

		return availableAdvertise;
	}

	private Master getMaster(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@GetMapping(produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public List<Master> getAllAdvertises(@RequestHeader("auth-token") String token) {
		if (token.equals("abhi12345")) {
			return advertises;
		}
		return null;

	
}

	@GetMapping(value="/{id}",produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public Master getSpecificAdvertises(@PathVariable("id") long id, @RequestHeader("auth-token") String token) {
		if (token.equals("abhi12345")) {
		for (Master advertise : advertises) {

			if (advertise.getId() == id) {
				return advertise;
	
		  }
	    }

		return null;
     }
		return null;
  }
	
	@DeleteMapping("/{stockId}")
	public String deleteStock(@PathVariable("id") long id,@RequestHeader("auth-token") String token) {
		if (token.equals("abhi12345")) {
		for (Master advertise : advertises) {
			if (advertise.getId() == id) {
				advertises.remove(advertise);
				return "Advertisement deleted with id " + id;
			}
		}
		return "Sorry, advertisement id is not available";
     
	   }
		return null;
	}
}
	

     




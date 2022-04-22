package com.zensar.olxlogin.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zensar.olxlogin.entity.User;

@RestController
@RequestMapping("/user")
public class OlxUserController {

	static List<User> users = new ArrayList<User>();

	static {
		users.add(new User(1, "Ram", "Nath", "ram123", "ramnath123", "ramnath@gmail.com", 1234567890));
		users.add(new User(2, "Virat", "Kohli", "virat123", "viratkohli123", "viratkohli@gmail.com", 1234567891));

	}

	@PostMapping(consumes= {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public String getLogIn(@RequestBody(required = false) String userName,
		@RequestBody(required = false) String password, @RequestHeader("auth-token") String token) {
		User users = new User();
		users.setFirstName("anand");
		users.setPassword("anand123");
		if (token.equals("abhi12345")) {
			if (users.getUserName().equals("Abhishek") && users.getPassword().equals("abhi@1234")) {
				return "Login Successful";
			} else {
				return "Sorry,Authentication Failed";
			}
		}
		return "xyz";
	}

	@PostMapping(produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},consumes= {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<User> registerUser(@RequestBody User user) {

		users.add(user);
		// else {
		// return new ResponseEntity<User>(user, HttpStatus.BAD_REQUEST);
		// }
		return new ResponseEntity<User>(user, HttpStatus.CREATED);

	}

	@DeleteMapping()

	public ResponseEntity<String> deleteAllUsers(@RequestHeader("auth-token") String token) {
		if (token.equals("abhi12345")) {
			users.removeAll(users);
			return new ResponseEntity<String>("All users logged out sucessfully", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Sorry, logout unsuccessful", HttpStatus.OK);

	}

	@DeleteMapping("/{userName}")
	public String deleteUser(@PathVariable("id") int id) {

		for (User user : users) {
			if (user.getId() == id) {
				users.remove(user);
				return "User logged out with Id " + id;
			}
		}
		return "Sorry, Logout Unsuccessful";

	}

	@GetMapping(produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public List<User> getAllUsers(@RequestHeader("auth-token") String token) {
		if (token.equals("abhi12345")) {
			return users;
		}
		return null;

	}

	@GetMapping(value="/{id}",produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public User getUser(@PathVariable("id") int id) {
		Optional<User> user1 = users.stream().filter(user -> user.getId() == id).findAny();
		if (user1.isPresent()) {
			return user1.get();
		} else {
			return user1.orElseGet(() -> {
				return new User();
			});
		}
	}

}

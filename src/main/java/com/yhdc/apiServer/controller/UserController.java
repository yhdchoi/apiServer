/**
 * Test API
 * User Controller
 * 
 * @Author YEONGHYUN CHOI
 * @Version 1.0.0
 * 
 */

package com.yhdc.apiServer.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yhdc.apiServer.dto.UserDto;
import com.yhdc.apiServer.model.User;
import com.yhdc.apiServer.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class UserController {
	private final UserService userService;

	// New
	@PostMapping("/api/newuser")
	public ResponseEntity<String> newUser(@RequestBody UserDto userDto) {

		log.info(userDto);
		HttpStatus status = HttpStatus.CREATED;
		String result = userService.newUser(userDto);

		if (result == "FAIL") {
			status = HttpStatus.BAD_REQUEST;
		}

		return new ResponseEntity<String>(result, status);
	}

	// Get By Id
	@GetMapping("/api/getuserbyid/{id}")
	public ResponseEntity<User> getUserById(@PathVariable Long id) {

		HttpStatus status = HttpStatus.OK;
		User result = userService.getUserById(id);

		if (result == null) {
			status = HttpStatus.NOT_FOUND;
		}

		return new ResponseEntity<User>(result, status);
	}

	// List
	@GetMapping("/api/listuser")
	public ResponseEntity<List<User>> listUser() {

		HttpStatus status = HttpStatus.OK;
		List<User> result = userService.listUser();

		if (result == null) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<>(result, status);
	}

	// Update
	@PutMapping("/api/updateuser/{id}")
	public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {

		HttpStatus status = HttpStatus.OK;
		String result = userService.updateUser(id, userDto);

		if (result == "FAIL") {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<String>(result, status);
	}

	// Delete
	@DeleteMapping("/api/deleteuser/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable Long id) {

		HttpStatus status = HttpStatus.OK;
		String result = userService.deleteUser(id);

		if (result == "USER NOT FOUND") {
			status = HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<String>(result, status);
	}
}

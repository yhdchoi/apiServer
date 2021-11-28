package com.yhdc.apiServer.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yhdc.apiServer.dto.UserDto;
import com.yhdc.apiServer.model.User;
import com.yhdc.apiServer.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;

	// Post
	@Transactional
	public String newUser(UserDto userDto) {

		log.info(userDto);

		String username = userDto.getUsername();
		String email = userDto.getEmail();

		try {
			User newUser = new User();
			newUser.setUsername(username);
			newUser.setEmail(email);

			userRepository.save(newUser);
			log.info("NewUser: "+ newUser);
			
			return "SUCCESS";

		} catch (Exception e) {
			// TODO: handle exception
			return "FAIL";
		}
	}

	// Get By Id
	@Transactional(readOnly = true)
	public User getUserById(Long id) {
		try {
			User user = userRepository.findById(id).get();
			return user;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	// List
	@Transactional(readOnly = true)
	public List<User> listUser() {

		List<User> result = userRepository.findAll();

		return result;
	}

	// Update
	@Transactional
	public String updateUser(Long id, UserDto userDto) {
		String newEmail = userDto.getEmail();

		try {
			User user = userRepository.findById(id).get();
			user.setEmail(newEmail);

			return "SUCCESS";
		} catch (Exception e) {
			// TODO: handle exception
			return "FAIL";
		}
	}


	// Delete
	@Transactional
	public String deleteUser(Long id) {
		try {
			userRepository.deleteById(id);
			return "SUCCESS";
		} catch (Exception e) {
			// TODO: handle exception
			return "USER NOT FOUND";
		}
	}
}

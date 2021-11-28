package com.yhdc.apiServer.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yhdc.apiServer.dto.MessageDto;
import com.yhdc.apiServer.model.Message;
import com.yhdc.apiServer.service.MessageService;
import com.yhdc.apiServer.type.ResponseType;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class MessageController {

	private final MessageService messageService;

	@PostMapping("/api/newmsg")
	public ResponseEntity<ResponseType> postMsg(@RequestBody MessageDto msg) {

		ResponseType resp = messageService.postMessage(msg);

		return new ResponseEntity<ResponseType>(resp, HttpStatus.OK);
	}

	@GetMapping("/api/msglist/{id}")
	public ResponseEntity<List<Message>> msgList(@PathVariable Long id) {

		log.info("ID: "+ id);
		List<Message> result = messageService.getMessage(id);
		log.info("Result : " + result);

		return new ResponseEntity<List<Message>>(result, HttpStatus.OK);
	}

}

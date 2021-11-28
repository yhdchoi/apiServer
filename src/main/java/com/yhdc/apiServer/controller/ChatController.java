package com.yhdc.apiServer.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yhdc.apiServer.dto.ChatDto;
import com.yhdc.apiServer.model.Chat;
import com.yhdc.apiServer.service.ChatService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ChatController {

	private final ChatService chatService;

	@RequestMapping("/api/newchat")
	public ResponseEntity<Chat> createChat(@RequestBody ChatDto chatDto) {

		Chat result = chatService.createChat(chatDto);
		log.info(result);
		return new ResponseEntity<Chat>(result, HttpStatus.OK);
	}

	@RequestMapping("/api/chatlist")
	public ResponseEntity<List<Chat>> getChatList() {
		List<Chat> list = chatService.getChatList();
		log.info(list);
		return new ResponseEntity<List<Chat>>(list, HttpStatus.OK);
	}
}

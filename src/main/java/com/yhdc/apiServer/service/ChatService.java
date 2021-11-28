package com.yhdc.apiServer.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yhdc.apiServer.dto.ChatDto;
import com.yhdc.apiServer.model.Chat;
import com.yhdc.apiServer.model.User;
import com.yhdc.apiServer.repository.ChatRepository;
import com.yhdc.apiServer.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Service
public class ChatService {

	private final ChatRepository chatRepository;
    private final UserRepository userRepository;
	
    @Transactional
	public Chat createChat(ChatDto chatDto) {
		
		User user = userRepository.findByUsername(chatDto.getUsername());
		log.info("User : " + user);
		List<String> toUser = new ArrayList<String>();
		toUser.add(chatDto.getToUsername());
		log.info("toUser : "+toUser);
		
		Chat newChat = new Chat();
		newChat.setTitle(chatDto.getTitle());
		newChat.setUser(user);		
		newChat.setToUsernames(toUser);
		
		chatRepository.save(newChat);
		
		return newChat;
	}
	
	@Transactional
	public List<Chat> getChatList() {
		return chatRepository.findAll();
	}
}

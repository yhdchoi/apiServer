package com.yhdc.apiServer.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yhdc.apiServer.dto.MessageDto;
import com.yhdc.apiServer.model.Chat;
import com.yhdc.apiServer.model.Message;
import com.yhdc.apiServer.repository.ChatRepository;
import com.yhdc.apiServer.repository.MessageRepository;
import com.yhdc.apiServer.type.ResponseType;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Service
public class MessageService {

	private final MessageRepository messageRepository;
	private final ChatRepository chatRepository;

	@Transactional
	public ResponseType postMessage(MessageDto msg) {
		
		Chat chatrm = chatRepository.findById(msg.getChatroomId()).get();
		log.info("ChatRM : "+ chatrm);
		
		Message newMsg = new Message();
		newMsg.setUsername(msg.getUsername());
		newMsg.setContent(msg.getContent());
		newMsg.setChat(chatrm);
		
		messageRepository.save(newMsg);
		log.info("NewMsg : " + newMsg);
		
		return ResponseType.SUCCESS;  
	}
	
	@Transactional
	public List<Message> getMessage(Long id) {
		log.info("********************* Starting Service");
		
		Chat chat = chatRepository.findById(id).get();
		log.info("Chat : " + chat);
		
		List<Message> result = messageRepository.findByChat(chat);
		log.info("MsgList : " + result);
		
		return result;
	}
}

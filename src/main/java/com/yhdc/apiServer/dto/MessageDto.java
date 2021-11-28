package com.yhdc.apiServer.dto;

import lombok.Data;

@Data
public class MessageDto {

	private Long chatroomId;
	private String username;
	private String content;
}

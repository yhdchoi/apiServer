package com.yhdc.apiServer.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yhdc.apiServer.model.Item;

import lombok.extern.log4j.Log4j2;

// @AutoConfigureMockMvc -> register in IOC
// @Transactional -> rollback Transaction

@Log4j2
@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class ItemControllerIntegreTest {

	@Autowired
	MockMvc mockMvc;

	@Test
	public void saveTest() throws Exception {
		log.info("saveTest Starts=====================");
		Item item = new Item(null, "title1", "content1");
		String content = new ObjectMapper().writeValueAsString(item);

		// when
		ResultActions resultActions = mockMvc.perform(post("/item/save").contentType(MediaType.APPLICATION_JSON)
				.content(content).accept(MediaType.APPLICATION_JSON));

		// then
		resultActions.andExpect(status().isCreated()).andExpect(jsonPath("$.title").value("title1"))
				.andDo(MockMvcResultHandlers.print());

	}
}

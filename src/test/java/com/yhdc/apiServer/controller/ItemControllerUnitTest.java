package com.yhdc.apiServer.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yhdc.apiServer.model.Item;
import com.yhdc.apiServer.service.ItemService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@WebMvcTest
public class ItemControllerUnitTest {

	@Autowired
	private MockMvc mockMvc;

	// registers in IOC environment
	@MockBean
	private ItemService itemService;

	@Test
	public void saveTest() throws Exception {
		log.info("saveTest Starts=====================");
		Item item = new Item(null, "title1", "content1");
		String content = new ObjectMapper().writeValueAsString(item);
		when(itemService.saveItem(item)).thenReturn(new Item(1L, "title1", "content1"));

		// when
		ResultActions resultActions = mockMvc.perform(post("/item/save").contentType(MediaType.APPLICATION_JSON)
				.content(content).accept(MediaType.APPLICATION_JSON));

		// then
		resultActions.andExpect(status().isCreated()).andExpect(jsonPath("$.title").value("title1"))
				.andDo(MockMvcResultHandlers.print());

	}

}

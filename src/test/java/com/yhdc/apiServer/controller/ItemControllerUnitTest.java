package com.yhdc.apiServer.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yhdc.apiServer.model.Item;
import com.yhdc.apiServer.service.ItemService;

@WebMvcTest
public class ItemControllerUnitTest {

	@Autowired
	private MockMvc mockMvc;

	// registers in IOC environment
	@MockBean
	private ItemService itemService;

	@Test
	public void saveTest() throws Exception {
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

	@Test
	public void findAllTest() throws Exception {
		// given
		List<Item> items = new ArrayList<>();
		items.add(new Item(1L, "title", "content1"));
		items.add(new Item(2L, "title", "content2"));
		when(itemService.findAllItems()).thenReturn(items);

		// when
		ResultActions resultActions = mockMvc.perform(get("/items").accept(MediaType.APPLICATION_JSON));

		// then
		resultActions.andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(2)))
				.andExpect(jsonPath("$.[0].title").value("title")).andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void findByIdTest() throws Exception {
		// given
		Long id = 1L;
		when(itemService.getItem(id)).thenReturn(new Item(1L, "title", "content"));

		// when
		ResultActions resultAction = mockMvc.perform(get("/item/{id}", id).accept(MediaType.APPLICATION_JSON));

		// then
		resultAction.andExpect(status().isOk()).andExpect(jsonPath("$.title").value("title"))
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void updateTest() throws Exception {
		// given
		Long id = 1L;
		Item item = new Item(null, "titleUpdate", "contentUpdate");
		String content = new ObjectMapper().writeValueAsString(item);
		when(itemService.updateItem(id, item)).thenReturn(new Item(1L, "titleUpdate", "contentUpdate"));

		// when
		ResultActions resultAction = mockMvc.perform(put("/item/update/{id}", id)
				.contentType(MediaType.APPLICATION_JSON).content(content).accept(MediaType.APPLICATION_JSON));
		// then
		resultAction.andExpect(status().isOk()).andExpect(jsonPath("$.title").value("titleUpdate"))
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void deleteTest() throws Exception {
		// given
		Long id = 1L;
		when(itemService.deleteItem(id)).thenReturn("OK");

		// when
		ResultActions resultAction = mockMvc.perform(delete("/item/delete/{id}", id).accept(MediaType.TEXT_PLAIN));

		// then
		resultAction.andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());

		MvcResult requestResult = resultAction.andReturn();
		String result = requestResult.getResponse().getContentAsString();

		assertEquals("OK", result);
	}

}

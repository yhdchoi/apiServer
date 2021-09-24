package com.yhdc.apiServer.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yhdc.apiServer.model.Item;
import com.yhdc.apiServer.repository.ItemRepository;

// @AutoConfigureMockMvc -> register in IOC
// @Transactional -> rollback Transaction

@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class ItemControllerIntegreTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private EntityManager entityManager;

	// ALTER TABLE item ALTER COLUMN id RESTART WITH 1
	@BeforeEach
	public void init() {
		entityManager.createNativeQuery("ALTER TABLE item AUTO_INCREMENT = 1").executeUpdate();
	}

	@Test
	public void saveTest() throws Exception {
		Item item = new Item(null, "title1", "content1");
		String content = new ObjectMapper().writeValueAsString(item);

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
		items.add(new Item(2L, "title2", "content2"));
		itemRepository.saveAll(items);

		// when
		ResultActions resultActions = mockMvc.perform(get("/items").accept(MediaType.APPLICATION_JSON));

		// then
		resultActions.andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(2)))
				.andExpect(jsonPath("$.[0].title").value("title")).andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void findByIdTest() throws Exception {
		// given
		Long id = 2L;

		List<Item> items = new ArrayList<>();
		items.add(new Item(null, "title", "content1"));
		items.add(new Item(null, "title2", "content2"));
		itemRepository.saveAll(items);

		// when
		ResultActions resultAction = mockMvc.perform(get("/item/{id}", id).accept(MediaType.APPLICATION_JSON));

		// then
		resultAction.andExpect(status().isOk()).andExpect(jsonPath("$.title").value("title2"))
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void updateTest() throws Exception {
		// given
		Long id = 1L;

		List<Item> items = new ArrayList<>();
		items.add(new Item(null, "title", "content1"));
		items.add(new Item(null, "title2", "content2"));
		itemRepository.saveAll(items);

		Item item = new Item(null, "titleUpdate", "contentUpdate");
		String content = new ObjectMapper().writeValueAsString(item);

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

		// when
		ResultActions resultAction = mockMvc.perform(delete("/item/delete/{id}", id).accept(MediaType.TEXT_PLAIN));

		// then
		resultAction.andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());

		MvcResult requestResult = resultAction.andReturn();
		String result = requestResult.getResponse().getContentAsString();

		assertEquals("OK", result);
	}

}

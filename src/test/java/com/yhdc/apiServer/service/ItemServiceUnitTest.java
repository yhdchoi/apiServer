package com.yhdc.apiServer.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.yhdc.apiServer.model.Item;
import com.yhdc.apiServer.repository.ItemRepository;

@ExtendWith(MockitoExtension.class)
public class ItemServiceUnitTest {

	// When running ItemService object, injects everything under @Mock from
	// ItemServiceUnitTest
	@InjectMocks
	private ItemService itemService;

	@Mock
	private ItemRepository itemRepository;

	@Test
	public void saveTest() {
		Item item = new Item();
		item.setName("title");
		item.setContent("content");

		// when
		when(itemRepository.save(item)).thenReturn(item);

		Item itemEntity = itemService.saveItem(item);

		assertEquals(itemEntity, item);
	}

}

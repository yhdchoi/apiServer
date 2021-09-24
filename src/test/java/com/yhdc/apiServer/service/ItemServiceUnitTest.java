package com.yhdc.apiServer.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.yhdc.apiServer.repository.ItemRepository;

@ExtendWith(MockitoExtension.class)
public class ItemServiceUnitTest {

	// When running ItemService object, injects everything under @Mock from
	// ItemServiceUnitTest
	@InjectMocks
	private ItemService itemService;

	@Mock
	private ItemRepository itemRepository;

}

package com.yhdc.apiServer.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import com.yhdc.apiServer.model.Item;

@Transactional
@AutoConfigureTestDatabase(replace = Replace.ANY) // Test with a fake DB // None = real
@DataJpaTest // automatically registers repository
public class ItemRepositoryUnitTest {

	@Autowired
	private ItemRepository itemRepository;

	@Test
	public void saveTest() {
		Item item = new Item(null, "title", "content");

		Item itemEntity = itemRepository.save(item);

		assertEquals("title", itemEntity.getName());
	}
}

package com.yhdc.apiServer.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@AutoConfigureTestDatabase(replace = Replace.ANY) // Test with a fake DB // None = real
@DataJpaTest // automatically registers repository
public class ItemRepositoryUnitTest {

	@Autowired
	private ItemRepository itemRepository;
	
	
}

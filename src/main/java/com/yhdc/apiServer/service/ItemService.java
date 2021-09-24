package com.yhdc.apiServer.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yhdc.apiServer.model.Item;
import com.yhdc.apiServer.repository.ItemRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ItemService {

	private final ItemRepository itemRepository;

	@Transactional
	public Item saveItem(Item item) {
		return itemRepository.save(item);
	}

	@Transactional(readOnly = true)
	public Item getItem(Long id) {
		return itemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Please check the ID."));
	}

	@Transactional(readOnly = true)
	public List<Item> findAllItems() {
		return itemRepository.findAll();
	}
	
	@Transactional
	public Item updateItem(Long id, Item item) {
		// Dirty Check
		Item itemEntity = itemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Please check the ID."));
		
		itemEntity.setName(item.getName());
		itemEntity.setContent(item.getContent());
		
		return itemEntity;
	}

	@Transactional
	public String deleteItem(Long id) {		
		itemRepository.deleteById(id);
		return "OK";
	}
}

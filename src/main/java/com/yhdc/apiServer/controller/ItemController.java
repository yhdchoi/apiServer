package com.yhdc.apiServer.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yhdc.apiServer.model.Item;
import com.yhdc.apiServer.service.ItemService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ItemController {

	private final ItemService itemService;

	@PostMapping("/item/save")
	public ResponseEntity<Item> saveItem(@RequestBody Item item) {
		Item result = itemService.saveItem(item);
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}

	@GetMapping("/item/{id}")
	public ResponseEntity<Item> getItem(@PathVariable Long id) {
		Item result = itemService.getItem(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/items")
	public ResponseEntity<List<Item>> findAll() {
		List<Item> result = itemService.findAllItems();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PutMapping("/item/update/{id}")
	public ResponseEntity<Item> updateItem(@PathVariable Long id, @RequestBody Item item) {
		Item result = itemService.updateItem(id, item);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@DeleteMapping("/item/delete/{id}")
	public ResponseEntity<String> deleteItem(@PathVariable Long id) {
		String result = itemService.deleteItem(id);
		return new ResponseEntity<String>(result, HttpStatus.ACCEPTED);
	}

}

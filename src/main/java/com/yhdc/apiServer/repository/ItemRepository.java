package com.yhdc.apiServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yhdc.apiServer.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
	
	

}

package com.casestudy.practise.orders.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.casestudy.practise.orders.model.Product;

@FeignClient(value = "product-service",url ="http://localhost:8082/product")
public interface PlacingOrder {
	
	@DeleteMapping("/decreaseQuant/{productId}/{quantity}")
	public Product decreaseItem(@PathVariable int productId,@PathVariable int quantity);
	
}

package com.casestudy.service;

import java.util.ArrayList;  
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.casestudy.entities.Cart;
import com.casestudy.entities.Items;
import com.casestudy.entities.Product;
import com.casestudy.exception.CartAlreadyExistsException;
import com.casestudy.exception.CartNotFoundException;
import com.casestudy.repository.CartRepository;


@Service
public class CartServiceImpl implements CartService {
	
	Logger logger= LoggerFactory.getLogger(CartServiceImpl.class);
	
	@Autowired
	private CartRepository cartRepository;
	

	
	
	@Override
	public Cart getcartById(int cartId) throws CartNotFoundException {
			
		return cartRepository.findById(cartId).orElseThrow(()-> new CartNotFoundException());
	}

	@Override
	public Cart updateCart(int cartId,Cart cart) throws CartNotFoundException {
		
		Cart updatedCart= cartRepository.findById(cartId).orElseThrow(()-> new CartNotFoundException());
		
		updatedCart.setCartId( cart.getCartId());
		updatedCart.setItems( cart.getItems());
		updatedCart.setTotalPrice( cart.getTotalPrice());
		
		cartRepository.save(updatedCart);
		
		return updatedCart;
		
	}

	@Override
	public List<Cart> getallcarts() {
		
		return cartRepository.findAll();
	}

	@Override
	public double cartTotal(Cart cart) {
		
		return cart.getTotalPrice();
	}

	@Override
	public Cart addCart(Cart cart) throws CartAlreadyExistsException {
//		cart.setCartId(seqService.getSequenceNum(cart.sequenceName));
		return cartRepository.save(cart);
	}

	@Override
	public void deleteCartById(int cartId) {
		cartRepository.deleteById(cartId);
		
	}

}
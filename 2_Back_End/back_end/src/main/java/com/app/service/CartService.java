package com.app.service;

import java.util.List;
import java.util.Optional;

import com.app.pojo.Cart;

public interface CartService {

	String addItemToCart(Long productId, Integer quntity, Long userId);

	List<Cart> getAllCartContents(Long userID);

	void deleteFromCart(Long cartId);

	void deleteAllFromCart(Long userID);

	Optional<Cart> findById(Long cartId);

	String updateQuantity(Long cartId, Integer quantity);
}

package com.app.service;

import java.util.List;
import java.util.Optional;

import com.app.pojo.Cart;

public interface CartService {

	String addItemToCart(Integer productId, Integer quntity, Integer userId);

	List<Cart> getAllCartContents(Integer userID);

	void deleteFromCart(Integer cartId);

	void deleteAllFromCart(Integer userID);

	Optional<Cart> findById(Integer cartId);

	String updateQuantity(Integer cartId, Integer quantity);
}

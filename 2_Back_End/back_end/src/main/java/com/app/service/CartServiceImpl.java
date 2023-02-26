package com.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.CartRepository;
import com.app.dao.ProductRepository;
import com.app.dao.StockRepository;
import com.app.dao.UserRepository;
import com.app.pojo.Cart;
import com.app.pojo.CartItem;
import com.app.pojo.Product;
import com.app.pojo.Stock;
import com.app.pojo.User;

@Service
@Transactional
public class CartServiceImpl implements CartService {
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private ProductRepository prodRepo;
	@Autowired
	private CartRepository cartRepo;
	@Autowired
	private StockRepository stockRepo;

	@Override
	public String addItemToCart(Long productId, Integer quntity, Long userId) {
		User customer = userRepo.findById(userId).get();
		Product product = prodRepo.findById(productId).get();
		Stock stock = stockRepo.findById(productId).get();
		if (stock.getQuantity() < quntity)
			return "We Only Have" + stock.getQuantity() + " " + stock.getUnit() + "(s) of" + product.getName() + " .";
		List<CartItem> cartItems = new ArrayList<CartItem>();
		cartItems.add(new CartItem(quntity));
		cartRepo.save(new Cart(quntity, cartItems, customer));
		return quntity + product.getName() + " Added to cart";
	}

	@Override
	public List<Cart> getAllCartContents(Long userID) {
		return cartRepo.findAllItemsByUser(userID);
	}

	@Override
	public String updateQuantity(Long cartId, Integer quantity) {
//		CartItem cartItem = cartRepo.findById(cartId).get().getCartitem();

		return null;
	}

	@Override
	public void deleteFromCart(Long cartId) {
		cartRepo.deleteById(cartId);
	}

	@Override
	public void deleteAllFromCart(Long userID) {
		cartRepo.deleteAll(cartRepo.findAllItemsByUser(userID));
	}

	@Override
	public Optional<Cart> findById(Long cartId) {
		return cartRepo.findById(cartId);
	}
}

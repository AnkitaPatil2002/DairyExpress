package com.app.pojo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cart")
public class Cart extends BaseEntity {
	private int quantity;

	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<CartItem> cartitem;

	@OneToOne
	@JoinColumn(name = "customer_id")
	private User currentCustomer;

	public Cart() {
	}

	public Cart(int quantity, List<CartItem> cartitem, User currentCustomer) {
		super();
		this.quantity = quantity;
		this.cartitem = cartitem;
		this.currentCustomer = currentCustomer;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public User getCurrentCustomer() {
		return currentCustomer;
	}

	public void setCurrentCustomer(User currentCustomer) {
		this.currentCustomer = currentCustomer;
	}

	public List<CartItem> getCartitem() {
		return cartitem;
	}

	public void setCartitem(List<CartItem> cartitem) {
		this.cartitem = cartitem;
	}

	@Override
	public String toString() {
		return "Cart [quantity=" + quantity + "]";
	}
}

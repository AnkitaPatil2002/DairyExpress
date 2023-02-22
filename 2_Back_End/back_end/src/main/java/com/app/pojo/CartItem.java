package com.app.pojo;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "cartItem")
public class CartItem extends BaseEntity {
	private int quntity;
	private double price;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "cart_id")
	private Cart cart;

	public CartItem() {
	}

	public CartItem(int quntity, double price) {
		super();
		this.quntity = quntity;
		this.price = price;
	}

	public int getQuntity() {
		return quntity;
	}

	public void setQuntity(int quntity) {
		this.quntity = quntity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	@Override
	public String toString() {
		return "CartItem Id " + getId() + "[quntity=" + quntity + ", price=" + price + ", product=" + product
				+ ", cart=" + cart + "]";
	}
}

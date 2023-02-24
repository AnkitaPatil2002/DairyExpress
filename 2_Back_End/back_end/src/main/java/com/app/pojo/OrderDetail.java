package com.app.pojo;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "order_details")
public class OrderDetail extends BaseEntity {
	private double price;
	private int quantity;

	@ManyToOne
	@JoinColumn(name = "order_id", nullable = false)
	private Order currentOrder;

	@ManyToOne
	@JoinColumn(name = "product_id", nullable = false)
	private Product selectedProduct;

	public OrderDetail() {

	}

	public OrderDetail(double price, int quantity, Order currentOrder, Product selectedProduct) {
		super();
		this.price = price;
		this.quantity = quantity;
		this.currentOrder = currentOrder;
		this.selectedProduct = selectedProduct;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Order getCurrentOrder() {
		return currentOrder;
	}

	public void setCurrentOrder(Order currentOrder) {
		this.currentOrder = currentOrder;
	}

	public Product getSelectedProduct() {
		return selectedProduct;
	}

	public void setSelectedProduct(Product selectedProduct) {
		this.selectedProduct = selectedProduct;
	}

	@Override
	public String toString() {
		return "OrderDetail Id " + getId() + " [price=" + price + ", quantity=" + quantity + "]";
	}
}
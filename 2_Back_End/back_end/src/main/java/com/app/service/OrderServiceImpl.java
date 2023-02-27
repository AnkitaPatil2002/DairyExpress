package com.app.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.AddressRepository;
import com.app.dao.CartRepository;
import com.app.dao.OrderDetailRepository;
import com.app.dao.OrderRepository;
import com.app.dao.PaymentRepository;
import com.app.dao.StockRepository;
import com.app.dao.UserRepository;
import com.app.dto.OrderResponse;
import com.app.pojo.Address;
import com.app.pojo.Cart;
import com.app.pojo.Order;
import com.app.pojo.OrderDetail;
import com.app.pojo.OrderStatus;
import com.app.pojo.Payment;
import com.app.pojo.PaymentStatus;
import com.app.pojo.Stock;
import com.app.pojo.Type;
import com.app.pojo.User;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderRepository orderRepo;

	@Autowired
	private OrderDetailRepository orderDetailRepo;

	@Autowired
	private CartRepository cartRepo;

	@Autowired
	private AddressRepository addrRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private StockRepository stockRepo;

	@Autowired
	private PaymentRepository payRepo;

	@Override
	public String placeOrderForUser(Long userId, Long addrId, String paymentMode) {
		// get all cart items for given user
		List<Cart> cartItems = cartRepo.findAllItemsByUser(userId);
		double total = 0.0;
		int deliveryCharges = 25;
		for (Cart item : cartItems) {
			Stock stock = stockRepo.findById(item.getSelectedProduct().getId()).get();
			int stockQuantity = stock.getQuantity();
			int orderQuantity = item.getQuantity();
			if (stockQuantity < orderQuantity) {
				return item.getSelectedProduct().getName() + " is out of stock. Order cannot be placed";
			}
			total += item.getQuantity() * item.getSelectedProduct().getPrice();
			stock.setQuantity(stockQuantity - orderQuantity);
		}
		Address address = addrRepo.findById(addrId).get();
		User user = userRepo.findById(userId).get();

		Order newOrder = new Order(total, LocalDateTime.now(), OrderStatus.PLACED, LocalDateTime.now(), address, user,
				null);
		orderRepo.save(newOrder);

		Payment payment = new Payment(total + deliveryCharges, LocalDateTime.now(),
				paymentMode.equals("COD") ? PaymentStatus.PENDING : PaymentStatus.COMPLETED, Type.valueOf(paymentMode),
				newOrder);
		payRepo.save(payment);
		cartItems.forEach(item -> {
			orderDetailRepo.save(new OrderDetail(item.getSelectedProduct().getPrice(), item.getQuantity(), newOrder,
					item.getSelectedProduct()));
		});
		cartRepo.deleteAll(cartItems);
		return "Order Placed Successfully!";
	}

	@Override
	public List<OrderResponse> getAllOrders() {
		List<Order> orders = orderRepo.findAll();
		List<OrderResponse> response = new ArrayList<>();

		for (Order order : orders) {
			List<OrderDetail> orderDetails = orderDetailRepo.findAllByOrderId(order.getId());
			Payment payment = payRepo.findPaymentByOrderId(order.getId());
			response.add(new OrderResponse(order, orderDetails, payment));
		}
		return response;
	}

	@Override
	public List<OrderResponse> getAllCustomerOrders(Long userId) {
		List<Order> orders = orderRepo.findAllOrdersByUserId(userId);

		List<OrderResponse> response = new ArrayList<>();

		for (Order order : orders) {
			List<OrderDetail> orderDetails = orderDetailRepo.findAllByOrderId(order.getId());
			Payment payment = payRepo.findPaymentByOrderId(order.getId());
			response.add(new OrderResponse(order, orderDetails, payment));
		}
		return response;
	}

	@Override
	public void assignEmployee(Long userId, Long orderId) {
		Order order = orderRepo.findById(orderId).get();
		User employee = userRepo.findById(userId).get();
		order.setEmployee(employee);
		if (employee.getRole().name().equals("EMPLOYEE")) {
			order.setOrderStatus(OrderStatus.PACKING);
			order.setStatusUdpateTime(LocalDateTime.now());
		} else if (employee.getRole().name().equals("DELIVERY_PERSON"))
			order.setOrderStatus(OrderStatus.OUT_FOR_DELIVERY);
		return;
	}

	@Override
	public List<OrderResponse> getAllAssignedOrders(Long userId) {
		List<Order> orders = orderRepo.findAllOrdersByEmployeeId(userId);

		List<OrderResponse> response = new ArrayList<>();

		for (Order order : orders) {
			List<OrderDetail> orderDetails = orderDetailRepo.findAllByOrderId(order.getId());
			Payment payment = payRepo.findPaymentByOrderId(order.getId());
			response.add(new OrderResponse(order, orderDetails, payment));
		}
		return response;
	}

	@Override
	public void updateOrderStatus(Long orderId, String status) {
		Order order = orderRepo.findById(orderId).get();
		order.setOrderStatus(OrderStatus.valueOf(status));
		order.setStatusUdpateTime(LocalDateTime.now());
		if (status.equals("DELIVERED")) {
			Payment payment = payRepo.findPaymentByOrderId(orderId);
			payment.setStatus(PaymentStatus.COMPLETED);
		}
		return;
	}
}

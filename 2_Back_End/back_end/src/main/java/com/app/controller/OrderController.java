package com.app.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.OrderPlaceDto;
import com.app.dto.ResponseDto;
import com.app.service.OrderService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@PostMapping("/place")
	public ResponseEntity<?> placeOrderFromCart(@RequestBody OrderPlaceDto orderInput) {
		Long userId = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName());
		Long addrId = orderInput.getAddressId();
		String paymentMode = orderInput.getPaymentMode();
		return new ResponseEntity<>(
				new ResponseDto<>("success", orderService.placeOrderForUser(userId, addrId, paymentMode)),
				HttpStatus.CREATED);
	}

	@GetMapping("/all")
	public ResponseEntity<?> getAllOrders() {
		return new ResponseEntity<>(new ResponseDto<>("success", orderService.getAllOrders()), HttpStatus.OK);
	}

	@GetMapping("/assigned")
	public ResponseEntity<?> getAssignedOrders() {
		Long userId = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName());
		return new ResponseEntity<>(new ResponseDto<>("success", orderService.getAllAssignedOrders(userId)),
				HttpStatus.OK);
	}

	@GetMapping("/customer/all")
	public ResponseEntity<?> getAllCustomerOrders() {
		Long userId = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName());
		return new ResponseEntity<>(new ResponseDto<>("success", orderService.getAllCustomerOrders(userId)),
				HttpStatus.OK);
	}

	@PatchMapping("/update")
	public ResponseEntity<?> assignEmployee(@RequestBody HashMap<String, Long> orderInput) {
		Long userId = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName());
		orderService.assignEmployee(userId, orderInput.get("orderId"));
		return new ResponseEntity<>(new ResponseDto<>("success", "Order Assigned Successfully!!"), HttpStatus.OK);
	}

	@PatchMapping("/update-status")
	public ResponseEntity<?> updateOrderStatus(@RequestBody HashMap<String, String> orderInput) {
		orderService.updateOrderStatus(Long.parseLong(orderInput.get("orderId")), orderInput.get("status"));
		return new ResponseEntity<>(new ResponseDto<>("success", "Order Status Changed Successfully!!"), HttpStatus.OK);
	}
}

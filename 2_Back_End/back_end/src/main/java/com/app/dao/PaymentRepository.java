package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.pojo.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
	@Query("Select p from Payment p where p.currentOrder.id=:id")
	Payment findPaymentByOrderId(@Param("id") Integer id);
}

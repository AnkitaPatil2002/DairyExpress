package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojo.Stock;

public interface StockRepository extends JpaRepository<Stock, Integer> {

}

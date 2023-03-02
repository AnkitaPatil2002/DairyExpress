package com.app.service;

import java.util.List;

import com.app.dto.ProductDto;
import com.app.pojo.Product;

public interface ProductService {

	String addProduct(ProductDto input);

	List<Product> getProductsByCategory(Integer id);

	ProductDto editProduct(ProductDto input);

	List<ProductDto> getStockReportByCategory(String category);

	String deleteProduct(Integer id);

	ProductDto getProductDetail(Integer id);
}

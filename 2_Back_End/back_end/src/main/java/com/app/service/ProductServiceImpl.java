package com.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.CategoryRepository;
import com.app.dao.ProductRepository;
import com.app.dao.StockRepository;
import com.app.dto.ProductDto;
import com.app.pojo.Category;
import com.app.pojo.Product;
import com.app.pojo.Status;
import com.app.pojo.Stock;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
	@Autowired
	private CategoryRepository categoryRepo;
	@Autowired
	private ProductRepository productRepo;
	@Autowired
	private StockRepository stockRepo;

	@Override
	public String addProduct(ProductDto input) {
		// find category by name
		Optional<Category> cat = categoryRepo.findByName(input.getCategory());
		Category currentCat = null;
		// Category present then get persistent pojo
		if (cat.isPresent())
			currentCat = cat.get();
		else {// Category absent then create a new category and persist it
			currentCat = new Category(input.getCategory(), Status.ACTIVE);
			categoryRepo.save(currentCat);
		}
		// extract product details
		Product product = input.getProduct();
		currentCat.addProduct(product);// helper method in category pojo,
		// Link the category pojo with product pojo by adding it into categories product
		// Extract stock from input
		Stock stock = input.getStock();
		// link the product with the stock pojo
		stock.setCurrentProduct(product);
		stockRepo.save(stock);
		return product.getName() + "Added Successfully";
	}

	@Override
	public List<Product> getProductsByCategory(Long id) {
		return productRepo.getProductByCategory(id);
	}

	@Override
	public ProductDto editProduct(ProductDto input) {
		// extract product from productDTO
		Product product = input.getProduct();
		// extract category from name
		Category cat = categoryRepo.findByName(input.getCategory()).get();
		// set the extracted category into product
		product.setSelectedcategory(cat);
		Product updateProduct = productRepo.save(product);
		// save stock
		Stock stock = stockRepo.save(input.getStock());
		return new ProductDto(updateProduct, cat.getName(), stock);
	}

	@Override
	public List<ProductDto> getStockReportByCategory(String category) {
		Category currentCat = categoryRepo.findByName(category).get();
		List<Product> products = productRepo.getProductByCategory(currentCat.getId());
		List<ProductDto> product_details = new ArrayList<>();
		products.forEach(product -> {
			product_details.add(new ProductDto(product, category, stockRepo.findById(product.getId()).get()));
		});
		return product_details;
	}

	@Override
	public String deleteProduct(Long id) {
		String productName = productRepo.findById(id).get().getName();
		stockRepo.deleteById(id);
		productRepo.deleteById(id);
		return productName + "Deleted Successfully";
	}

	@Override
	public ProductDto getProductDetail(Long id) {
		Stock stock = stockRepo.findById(id).get();
		return new ProductDto(stock.getCurrentProduct(), stock.getCurrentProduct().getSelectedcategory().getName(),
				stock);
	}

}

package com.EazyBuy.service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.EazyBuy.exception.ProductException;
import com.EazyBuy.model.Category;
import com.EazyBuy.model.Product;
import com.EazyBuy.repository.CategoryRepository;
import com.EazyBuy.repository.ProductRepository;
import com.EazyBuy.request.CreateProductRequest;

@Service
public class ProductServiceImplementation implements ProductService {

	private ProductRepository productRepository;
	private UserService userService;
	private CategoryRepository categoryRepository;

	public ProductServiceImplementation(ProductRepository productRepository, UserService userService,
			CategoryRepository categoryRepository) {
		this.productRepository = productRepository;
		this.userService = userService;
		this.categoryRepository = categoryRepository;
	}

	@Override
	public Product createProduct(CreateProductRequest req) throws ProductException, SQLException {
		System.out.println("reached here");
		Category category = categoryRepository.findByName(req.getCategory());
		if (category == null) {
			Category newCategory = new Category();
			newCategory.setName(req.getCategory());
			System.out.println("category is getting created");
			category = categoryRepository.save(newCategory);
			System.out.println("category created");
		}
		Product product = new Product();
		product.setTitle(req.getTitle());
		product.setColor(req.getColor());
		product.setDescription(req.getDescription());
		product.setImageUrl(req.getImageUrl());
		product.setBrand(req.getBrand());
		product.setPrice(req.getPrice());
		product.setSizes(req.getSize());
		product.setQuantity(req.getQuantity());
		product.setCreatedAt(LocalDateTime.now());
		product.setCategory(category);

		Product savedProduct = productRepository.save(product);
		System.out.println("/saved product");

		return savedProduct;
	}

	@Override
	public Page<Product> getProductsSortedByPrice(String sortDirection, int page, int pageSize) {
		Pageable pageable = PageRequest.of(page, pageSize);

		if ("asc".equalsIgnoreCase(sortDirection)) {
			return productRepository.findAllByOrderByPriceAsc(pageable);
		} else if ("desc".equalsIgnoreCase(sortDirection)) {
			return productRepository.findAllByOrderByPriceDesc(pageable);
		} else {
			return productRepository.findAllByOrderByPriceAsc(pageable);
		}
	}

	@Override
	public Page<Product> getProductsByCategory(String categoryName, int page, int pageSize) {
		Pageable pageable = PageRequest.of(page, pageSize);
		return productRepository.findByCategoryNameIgnoreCase(categoryName, pageable);
	}

	@Override
	public Page<Product> getProductsByCategoryAndPriceRange(String categoryName, Integer minPrice, Integer maxPrice,
			int page, int pageSize) {
		Pageable pageable = PageRequest.of(page, pageSize);
		return productRepository.findByCategoryNameIgnoreCaseAndPriceBetween(categoryName, minPrice, maxPrice,
				pageable);
	}

	@Override
	public String deleteProduct(Long productId) throws ProductException {

		Product product = findProductById(productId);

		System.out.println("delete product " + product.getId() + " - " + productId);
		product.getSizes().clear();
//		productRepository.save(product);
//		product.getCategory().
		productRepository.delete(product);

		return "Product deleted Successfully";
	}

	@Override
	public Product updateProduct(Long productId, Product req) throws ProductException {
		Product product = findProductById(productId);

		if (req.getQuantity() != 0) {
			product.setQuantity(req.getQuantity());
		}
		if (req.getDescription() != null) {
			product.setDescription(req.getDescription());
		}

		return productRepository.save(product);
	}

	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public Product findProductById(Long id) throws ProductException {
		Optional<Product> opt = productRepository.findById(id);

		if (opt.isPresent()) {
			return opt.get();
		}
		throw new ProductException("product not found with id " + id);
	}

	@Override
	public List<Product> findProductByCategory(String category) {

		System.out.println("category --- " + category);

		List<Product> products = productRepository.findByCategory(category);

		return products;
	}

	@Override
	public List<Product> searchProduct(String query) {
		List<Product> products = productRepository.searchProduct(query);
		return products;
	}

	@Override
	public Page<Product> getAllProduct(String category, List<String> colors, List<String> sizes, Integer minPrice,
			Integer maxPrice, String sort, String stock, Integer pageNumber, Integer pageSize) {

		Pageable pageable = PageRequest.of(pageNumber, pageSize);

		List<Product> products = productRepository.filterProducts(category, minPrice, maxPrice, sort);

		if (!colors.isEmpty()) {
			products = products.stream().filter(p -> colors.stream().anyMatch(c -> c.equalsIgnoreCase(p.getColor())))
					.collect(Collectors.toList());

		}

		if (stock != null) {

			if (stock.equals("in_stock")) {
				products = products.stream().filter(p -> p.getQuantity() > 0).collect(Collectors.toList());
			} else if (stock.equals("out_of_stock")) {
				products = products.stream().filter(p -> p.getQuantity() < 1).collect(Collectors.toList());
			}

		}
		int startIndex = (int) pageable.getOffset();
		int endIndex = Math.min(startIndex + pageable.getPageSize(), products.size());

		List<Product> pageContent = products.subList(startIndex, endIndex);
		Page<Product> filteredProducts = new PageImpl<>(pageContent, pageable, products.size());
		return filteredProducts; // If color list is empty, do nothing and return all products

	}

}

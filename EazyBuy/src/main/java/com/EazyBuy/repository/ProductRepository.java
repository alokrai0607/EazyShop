package com.EazyBuy.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.EazyBuy.model.Product;


public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query("SELECT p From Product p Where LOWER(p.category.name)=:category")
	public List<Product> findByCategory(@Param("category") String category);
	
	@Query("SELECT p From Product p where LOWER(p.title) Like %:query% OR LOWER(p.description) Like %:query% OR LOWER(p.brand) LIKE %:query% OR LOWER(p.category.name) LIKE %:query%")
	public List<Product> searchProduct(@Param("query")String query);
	


	
	@Query("SELECT p FROM Product p " +
	        "WHERE (p.category.name = :category OR :category = '') " +
	        "AND ((:minPrice IS NULL AND :maxPrice IS NULL) OR (p.price BETWEEN :minPrice AND :maxPrice)) " +
		    "ORDER BY " +
		    "CASE WHEN :sort = 'price_low' THEN p.price END ASC, " +
		    "CASE WHEN :sort = 'price_high' THEN p.price END DESC")
	List<Product> filterProducts(
	        @Param("category") String category,
			@Param("minPrice") Integer minPrice,
			@Param("maxPrice") Integer maxPrice,
			@Param("sort") String sort
			);
	Page<Product> findAllByOrderByPriceAsc(Pageable pageable); 
    Page<Product> findAllByOrderByPriceDesc(Pageable pageable);
    Page<Product> findByCategoryNameIgnoreCase(String categoryName, Pageable pageable);
    Page<Product> findByCategoryNameIgnoreCaseAndPriceBetween(
            String categoryName, Integer minPrice, Integer maxPrice, Pageable pageable);
}

package com.EazyBuy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.EazyBuy.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

//	@Query("select p from product inner join category_product cp on p.product_product_id=cp.category_cat_id inner join category c on (select catId from category where catName=?1)= cp.category_cat_id")
//	public List<Product> getAllProductByCategoryName(String name);
	
	@Query("SELECT p FROM Product p WHERE p.productName = :productName")
    Product findProductByProductName(String productName);
}

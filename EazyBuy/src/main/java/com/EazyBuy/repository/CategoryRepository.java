package com.EazyBuy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.EazyBuy.model.Category;
import com.EazyBuy.model.CategoryEnum;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{

	public List<Category> findByCatName(CategoryEnum catName);


	
}
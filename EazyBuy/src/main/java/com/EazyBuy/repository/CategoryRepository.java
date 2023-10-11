package com.EazyBuy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.EazyBuy.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

	public Category findByName(String name);


	
}
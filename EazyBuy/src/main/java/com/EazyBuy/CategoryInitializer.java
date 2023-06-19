package com.EazyBuy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.EazyBuy.model.Category;
import com.EazyBuy.model.CategoryEnum;
import com.EazyBuy.repository.CategoryRepository;

//@Component
public class CategoryInitializer {

	@Autowired
    private  CategoryRepository categoryRepository;

 
	@EventListener(ApplicationReadyEvent.class)
	public void initializeCategories() {
	    for (CategoryEnum categoryEnum : CategoryEnum.values()) {
	        Category extCategory = categoryRepository.findByCatName(categoryEnum);
	        if (extCategory == null) {
	            Category category = new Category();
	            category.setCatName(categoryEnum);
	            categoryRepository.save(category);
	        }
	    }
	}

}

//gfor git

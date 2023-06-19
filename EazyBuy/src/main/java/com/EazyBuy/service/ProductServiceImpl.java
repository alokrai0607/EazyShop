package com.EazyBuy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EazyBuy.exception.ProductException;
import com.EazyBuy.exception.UserException;
import com.EazyBuy.model.Category;
import com.EazyBuy.model.CurrentUserSession;
import com.EazyBuy.model.Product;
import com.EazyBuy.repository.CategoryRepository;
import com.EazyBuy.repository.ProductRepository;
import com.EazyBuy.repository.UserSession;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository proRepo;
	
	@Autowired
	private CategoryRepository catRepo;
	
	@Autowired
	private UserSession userRepo;
	
	
	
	@Override
	public List<Product> viewAllProduct() throws ProductException {
		List<Product> productList = proRepo.findAll();
		if(productList.isEmpty()) {
			throw new ProductException("No product in the List");
		}
		return productList;
	}
	
	
	
	

	@Override
	public Product addProduct(Product product) throws ProductException {
		
		

	
		
		Optional<Product> prod = proRepo.findById(product.getProductId());
		
		if(prod.isPresent()) {
			throw new ProductException(" Product is Already there");
		}
		
		 
		return proRepo.save(product);
	}
	
	
	
	

	@Override
	public Product updateProduct(Product product) throws ProductException {
		
	
		
		
		Optional<Product> prod = proRepo.findById(product.getProductId());
		if(prod.isPresent()==false) {
			throw new ProductException("No Product is present in the List");
		}
		
		Product p = prod.get();
		p.setPrice(product.getPrice());
		p.setProductName(product.getProductName());
		p.setImage(product.getImage());
		Category existingCategory = p.getCategory();
		Category newCategory = product.getCategory();
		if (newCategory != null) {
		existingCategory.setCatName(newCategory.getCatName());
		existingCategory.setProduct(newCategory.getProduct());
		}
		
		p.setDescription(product.getDescription());
		p.setManufacturer(product.getManufacturer());
		p.setQuantity(product.getQuantity());
		
		return proRepo.save(p);
		
	}
	

	@Override
	public Product getProductById(Integer id) throws ProductException {
		
		Optional<Product> prod = proRepo.findById(id);
		if(prod.isPresent()==false) {
			throw new ProductException("No Product is present in the List");
		}
		return prod.get();
	}
	
	
	
	
	@Override
	public Product deleteProductById(Integer id) throws ProductException, UserException {
	    // Check if user is authenticated
	    
	    // Check if product exists
	    Optional<Product> optionalProduct = proRepo.findById(id);
	    if (!optionalProduct.isPresent()) {
	        throw new ProductException("Product not found.");
	    }
	    
	    // Delete the product and return it
	    Product product = optionalProduct.get();
	    proRepo.deleteById(id);
	    return product;
	}
//	public Product deleteProductById(Integer id, String Key) throws ProductException {
//		
//		
//		  CurrentUserSession user = userRepo.findByUuid(Key);
//			
//			if(user==null) {
//				throw new UserException("Please Login first");
//			}
//			
//		
//		Optional<Product> prod = proRepo.findById(id);
//		if(prod.isPresent()==false) {
//			throw new ProductException("No Product is present in the List");
//		}
//		Product p = prod.get();
//		proRepo.deleteById(id);
//		return p;
//	}



	
	
	@Override
	public Category addCategory(Category category) throws ProductException {
		
		
		
		Optional<Category> cat = catRepo.findById(category.getCatId());
		
		if(cat.isPresent()) {
			throw new ProductException("category is Already present");
		}
		 
		return catRepo.save(category);
	}
	
	
	
	
	
	@Override
	public Category deleteCategory(Integer catId) throws ProductException {
		
	
			
		
		Optional<Category> cat = catRepo.findById(catId);
		
		if(cat.isPresent()==false) {
			throw new ProductException("category is not present");
		}
		catRepo.deleteById(catId);
		return cat.get();
	}





	 @Override
	    public Product findProductByProductName(String productName) throws ProductException {
	        Product product = proRepo.findProductByProductName(productName);
	        if (product == null) {
	            throw new ProductException("Product not found for productName: " + productName);
	        }
	        return product;
	 }

	
}

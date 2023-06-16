package com.EazyBuy.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EazyBuy.exception.CartException;
import com.EazyBuy.exception.UserException;
import com.EazyBuy.model.Cart;
import com.EazyBuy.model.CurrentUserSession;
import com.EazyBuy.model.Product;
import com.EazyBuy.repository.CartRepository;
import com.EazyBuy.repository.CustomerRepo;
import com.EazyBuy.repository.ProductRepository;
import com.EazyBuy.repository.UserSession;

@Service
public class CartServiceImp implements CartService {

	@Autowired
	public CartRepository cRepo;

	@Autowired
	public ProductRepository pRepo;
	
	@Autowired
	public UserSession userRepo;

	
	@Autowired
	public CustomerRepo cusRepo;

	
	
	
	
	

	@Override
	public Cart addProductToCart(Integer cartId, Integer productId) throws CartException {

		

		Optional<Cart> opCart = cRepo.findById(cartId);

		if (opCart.isEmpty()) {
			throw new CartException("Cart does not exists");
		}
		
	

     	Cart cart = opCart.get();

		
	    Optional<CurrentUserSession >optionalSession=userRepo.findById( cart.getCustomer().getCustomerId());	
	    
	    if (!optionalSession.isPresent()) {
			throw new UserException("Login first !");
		}
			         

	    
		Optional<Product> opProduct = pRepo.findById(productId);

		if (opProduct.isEmpty()) {
			throw new CartException("Product with productId " + productId + " does not exixts !");
		}
		
	
		Product p = opProduct.get();
		
		
		if(cart.getProduct().contains(p)) {
			throw new CartException("Product with productId " + productId + " is already present in the Cart");
		}

		p.setQuantity(1);

//		p.setCart(cart);
//		
		cart.getProduct().add(p);

		cart.getCustomer().setCart(cart);

		return cRepo.save(cart);

	}
	
	
	
	
	
	

	@Override
	public Cart removeProductFromCart(Integer cartId, Integer productId) throws CartException {

		Optional<Cart> opCart = cRepo.findById(cartId);

		Cart cart = opCart.get();
		
		 Optional<CurrentUserSession >optionalSession=userRepo.findById( cart.getCustomer().getCustomerId());	
		    
		    if (!optionalSession.isPresent()) {
				throw new UserException("Login first !");
			}
		    
		    
        boolean flag=false;
        
		List<Product> productList = cart.getProduct();

		for (Product pro : productList) {

			if (pro.getProductId() == productId) {

				productList.remove(pro);
				
				flag=true;
			}
		}
		
		if(flag==false) {
			throw new CartException("Product does not exists with productId "+productId+ " !");
		}

		cart.setProduct(productList);
		cRepo.save(cart);
		return cart;

	}
	
	

	@Override
	public Cart updateProductQantity(Integer cartId, Integer productId, Integer quantity) throws CartException {

		Optional<Cart> opCart = cRepo.findById(cartId);

		if (opCart.isEmpty()) {
			throw new CartException("Cart is Empty");
		}

		Cart cart = opCart.get();
		
		 Optional<CurrentUserSession >optionalSession=userRepo.findById( cart.getCustomer().getCustomerId());	
		    
		    if (!optionalSession.isPresent()) {
				throw new UserException("Login first !");
			}
		    

		Optional<Product> opProduct = pRepo.findById(productId);

		if (opProduct.isEmpty()) {
			throw new CartException("Product with productId " + productId + " does not exists in the cart !");
		}

		List<Product> productList = cart.getProduct();

		for (Product pro : productList) {

			if (pro.getProductId() == productId) {

				pro.setQuantity(pro.getQuantity()+quantity);
			}
		}

		cart.setProduct(productList);

		return cRepo.save(cart);

	}
	
	
	
	
	

	@Override
	public Cart removeAllProducts(Integer cartId) throws CartException {

		Optional<Cart> opCart = cRepo.findById(cartId);
		if (opCart.isEmpty()) {
			throw new CartException("Cart is Empty");
		}

		Cart cart = opCart.get();
		
		 Optional<CurrentUserSession >optionalSession=userRepo.findById( cart.getCustomer().getCustomerId());	
		    
		    if (!optionalSession.isPresent()) {
				throw new UserException("Login first !");
			}

		List<Product> productList = cart.getProduct();

		productList = new ArrayList<>();

		cart.setProduct(productList);

		return cRepo.save(cart);

	}

	
	
	
	
	@Override
	public List<Product> viewAllProducts(Integer cartId) throws CartException {

		Optional<Cart> opCart = cRepo.findById(cartId);
		if (opCart.isEmpty()) {
			throw new CartException("Cart is Empty");
		}

		Cart cart = opCart.get();
		
		 Optional<CurrentUserSession >optionalSession=userRepo.findById( cart.getCustomer().getCustomerId());	
		    
		    if (!optionalSession.isPresent()) {
				throw new UserException("Login first !");
			}

		return cart.getProduct();

	}

}

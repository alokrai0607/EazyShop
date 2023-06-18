package com.EazyBuy.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EazyBuy.exception.CartException;
import com.EazyBuy.exception.OrderException;
import com.EazyBuy.exception.UserException;
import com.EazyBuy.model.Cart;
import com.EazyBuy.model.CurrentUserSession;
import com.EazyBuy.model.Customer;
import com.EazyBuy.model.Orders;
import com.EazyBuy.model.Product;
import com.EazyBuy.model.ProductDTO;
import com.EazyBuy.repository.CartRepository;
import com.EazyBuy.repository.CustomerRepo;
import com.EazyBuy.repository.OrderRepository;
import com.EazyBuy.repository.UserSession;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private UserSession userRepo;
	
	@Autowired
	private CustomerRepo customerRepo;
	
	@Autowired
	private CartRepository cartRepository;
	

	@Override
	public Orders addOrder(Integer customerId) throws OrderException, CartException {
		CurrentUserSession loggedInUser = userRepo.findById(customerId).orElseThrow(()-> 
											new UserException("Please provide a valid customerId or Login First"));
		
		Orders newOrder = new Orders();
		
		Optional<Customer> loggedCus= customerRepo.findById(customerId);
		
		if(customerId == loggedInUser.getUserId()) {
			Optional<Cart> cart= cartRepository.findById(customerId);
			
			Cart cartDetailes= cart.get();
			
			
			
			List<Product> list= cartDetailes.getProduct();
			
			if(list.isEmpty()) {
				throw new UserException("Add products to cart first");
			}
			
			List<ProductDTO> listDTO= new ArrayList<>();
			
			for(Product p: list) {
				ProductDTO pDTO= new ProductDTO();
				
				pDTO.setProductId(p.getProductId());
				pDTO.setProductName(p.getProductName());
				pDTO.setPrice(p.getPrice());
				pDTO.setDescription(p.getDescription());
				pDTO.setManufacturer(p.getManufacturer());
				pDTO.setQuantity(p.getQuantity());
				
				listDTO.add(pDTO);
				
			}
			
			cartDetailes.setProduct(null);
			cartRepository.save(cartDetailes);
			
			newOrder.setOrderDate(LocalDate.now());
			newOrder.setOrderStatus("Order Confirm");
			newOrder.setProductList(listDTO);
			newOrder.setCustomer(loggedCus.get());
			newOrder.setAddress(loggedCus.get().getAddress());
			
			return orderRepository.save(newOrder);
			
		}
		else
		throw new UserException("Invalid customer details, please login first");
		
		
		
		
		
		
//		
//		CurrentUserSession user = userRepo.findByUuid(key);
//		
//		if(user==null) {
//			throw new UserException("Please Login first");
//		}
//		
//		Integer id = user.getUserId();
//		
//		Optional<Customer> cs = customerRepo.findById(id);
//		
//		Customer customer = cs.get();
//		
//		
//		
//		Address address = customer.getAddress();
//		
//		List<Product> productList = customer.getCart().getProduct();
//		if(productList.isEmpty()) {
//			throw new CartException("Product List is empty");
//		}
//		Orders orders = new Orders();
//		
//		orders.setAddress(address);
//		orders.setCustomer(customer);
//		orders.setOrderDate(LocalDate.now());
//		orders.setOrderStatus("confirmed");
//		orders.setProduct(productList);
//		
//		return orderRepository.save(orders);
//		
	}

	@Override
	public Orders updateOrder(Orders order) throws OrderException {
		
		
		Optional<Orders> or = orderRepository.findById(order.getOrderId());

		if (or.isPresent()) {

			orderRepository.save(order);

		} else {
			throw new OrderException("Order does not exist with this order id : " + order.getOrderId());
		}

		return order;
	}

	@Override
	public String removeOrder(Integer orderId) throws OrderException {

	    
	        Optional<Orders> order = orderRepository.findById(orderId);
	        
	        Integer cusId= order.get().getCustomer().getCustomerId();
	        
	        Customer customer= order.get().getCustomer();
	        
	        List<Orders> orderList= customer.getOrders();
	        
	        for (Iterator<Orders> iterator = orderList.iterator(); iterator.hasNext();) {
	            Orders or = iterator.next();
	            if (or.getOrderId() == orderId) {
	                iterator.remove();
	                break;
	            }
	        }
	        
	        customer.setOrders(orderList);
	        customerRepo.save(customer);
	        
	        orderRepository.delete(order.get());
	        return "Order deleted !";
	   
		
		
		
		
		
		
		
		
//		CurrentUserSession user = userRepo.findByUuid(key);
//		
//		if(user==null) {
//			throw new UserException("Please Login first");
//		}
//
//
//		Optional<Orders> order= orderRepository.findById(orderId);
//		
//		if(!order.isPresent()) {
//			throw new OrderException("Order does not exist with id :  "+ orderId);
//		}
//		
//		orderRepository.deleteById(order.get().getOrderId());
//		return order.get();
	}

	@Override
	public Orders viewOrderById(Integer orderId) throws OrderException {
		Orders or = orderRepository.findById(orderId)
				.orElseThrow(() -> new OrderException("Order does not exist with id : " + orderId));

		return or;
	}

	@Override
	public List<Orders> AllOrder() throws OrderException {

		List<Orders> allOrders = orderRepository.findAll();

		if (allOrders.isEmpty()) {
			throw new OrderException("There is No Order in Database");
		}

		return allOrders;
	}

	@Override
	public List<Orders> AllOrderByDate(LocalDate date) throws OrderException {

		List<Orders> allOrderdate = orderRepository.findByOrderDate(date);

		if (allOrderdate.isEmpty()) {
			throw new OrderException("There is No Order On this date : " + date);
		}

		return allOrderdate;
	}

	@Override
	public List<Orders> AllOrderByLocation(String location) throws OrderException {



		return null;
	}

}

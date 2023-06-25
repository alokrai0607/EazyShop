package com.EazyBuy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.EazyBuy.exception.CartException;
import com.EazyBuy.exception.CustomerException;
import com.EazyBuy.exception.OrderException;
import com.EazyBuy.model.Address;
import com.EazyBuy.model.Cart;
import com.EazyBuy.model.Category;
import com.EazyBuy.model.Customer;
import com.EazyBuy.model.Orders;
import com.EazyBuy.model.Product;
import com.EazyBuy.service.AddressService;
import com.EazyBuy.service.CartService;
import com.EazyBuy.service.CategoryService;
import com.EazyBuy.service.CustomerService;
import com.EazyBuy.service.OrderService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@CrossOrigin("*")
public class CustomerController {
	@Autowired
	private CustomerService customerService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AddressService addressService;

	@Autowired
	public CartService cartService;

	@Autowired
	private CategoryService cs;

	@Autowired
	private OrderService orService;

	@PostMapping("/customerSave")
	public ResponseEntity<Customer> saveCustmerHandller(@RequestBody Customer customer) {
		

		customer.setPassword(passwordEncoder.encode(customer.getPassword()));
		customer.setRole("ROLE_" + customer.getRole().toUpperCase());

		Customer customer2 = customerService.saveCustomer(customer);
		return new ResponseEntity<>(customer2, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/customerDelete")
	public ResponseEntity<Customer> deleteCustmerHandller(@Valid @RequestBody Customer customer) {
		Customer customer2 = customerService.deleteCustomer(customer.getCustomerId());
		return new ResponseEntity<>(customer2, HttpStatus.OK);
	}

	@PutMapping("/customerUpdate")
	public ResponseEntity<Customer> updateCustmerHandller(@Valid @RequestBody Customer customer) {
		Customer customer2 = customerService.updateCustomer(customer);
		return new ResponseEntity<>(customer2, HttpStatus.OK);
	}

	@GetMapping("/customerGet/{id}")
	public ResponseEntity<Customer> getCustmerByIdHandller(@Valid @PathVariable Integer id) {
		Customer customer2 = customerService.getCustomerByID(id);
		return new ResponseEntity<>(customer2, HttpStatus.OK);
	}

	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> viewAllCustomersHandller() {
		List<Customer> customers = customerService.getAllCustomer();
		return new ResponseEntity<>(customers, HttpStatus.OK);
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "logut successully";
	}

	@GetMapping("/signIn")
	public ResponseEntity<String> getLoggedInCustomerDetailsHandler(Authentication auth) {

		System.out.println(auth); // this Authentication object having Principle object details

		Customer customer = customerService.getCustomerDetailsByEmail(auth.getName());

		return new ResponseEntity<>(customer.getFirstName() + " Logged In Successfully", HttpStatus.ACCEPTED);
	}

	@GetMapping("/address/{aid}")
	public ResponseEntity<Address> getAddressByIdHanller(@PathVariable Integer aid) throws CustomerException {
		Address address = addressService.getAddress(aid);
		return new ResponseEntity<>(address, HttpStatus.OK);
	}

	@GetMapping("/address")
	public ResponseEntity<List<Address>> getAddressByIdHanller() throws CustomerException {
		List<Address> address = addressService.getAllAddress();
		return new ResponseEntity<>(address, HttpStatus.OK);
	}

	@PostMapping("/customers/{customerId}/addresses")
	public ResponseEntity<Address> createAddressForCustomer(@PathVariable Integer customerId,
			@RequestBody Address address) throws CustomerException {
		Customer customer = customerService.getCustomerByID(customerId);
		address.setCustomer(customer);
		Address savedAddress = addressService.createAddress(address, customerId);
		return new ResponseEntity<>(savedAddress, HttpStatus.CREATED);
	}

	@PostMapping("carts/addProduct")
	public ResponseEntity<Cart> addProductToCart(@RequestParam("cartId") String cartId,
			@RequestParam("productId") String productId) throws CartException {
		Integer cid = Integer.parseInt(cartId);
		Integer pid = Integer.parseInt(productId);
		Cart cart = cartService.addProductToCart(cid, pid);

		ResponseEntity<Cart> re = new ResponseEntity<>(cart, HttpStatus.CREATED);

		return re;

	}

//  DELETE PRODUCTS BY FROM CART ==========================>

	@DeleteMapping("carts/deletProduct")
	public ResponseEntity<Cart> removeProductFromCart(@RequestParam("cartId") Integer cartId,
			@RequestParam("productId") Integer productId) throws CartException {

		Cart cart = cartService.removeProductFromCart(cartId, productId);

		ResponseEntity<Cart> re = new ResponseEntity<>(cart, HttpStatus.CREATED);

		return re;

	}

//  UPDATE PRODUCTS BY QUANTITY ==========================>

	@PatchMapping("carts/updateProduct")
	public ResponseEntity<Cart> updateProductQuantity(@RequestParam("cartId") Integer cartId,
			@RequestParam("productId") Integer productId, @RequestParam("quantity") Integer quantity)
			throws CartException {

		Cart cart = cartService.updateProductQantity(cartId, productId, quantity);

		ResponseEntity<Cart> re = new ResponseEntity<>(cart, HttpStatus.CREATED);

		return re;

	}

	// REMOVE ALL PRODUCTS ==========================>

	@GetMapping("carts/emptyCart")
	public ResponseEntity<Cart> removeAllProducts(@RequestParam("cartId") Integer cartId) throws CartException {

		Cart cart = cartService.removeAllProducts(cartId);

		ResponseEntity<Cart> re = new ResponseEntity<>(cart, HttpStatus.CREATED);

		return re;

	}

	// VIEW ALL PRODUCTS =============================>

	@GetMapping("carts/viewProducts")
	public ResponseEntity<List<Product>> viewAllProducts(@RequestParam("cartId") Integer cartId) throws CartException {

		List<Product> productList = cartService.viewAllProducts(cartId);

		ResponseEntity<List<Product>> re = new ResponseEntity<>(productList, HttpStatus.CREATED);

		return re;

	}

	@GetMapping("/category/name")
	public ResponseEntity<List<Product>> getAllProductsHandler(@RequestParam String category) {

		List<Product> list = cs.getAllProductsByCategory(category);

		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@GetMapping("/category")
	public ResponseEntity<List<Category>> getAllProductsHandler() {

		List<Category> list = cs.getAllCategoty();

		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@PostMapping("/orders")
	public ResponseEntity<Orders> addOrderController(@RequestParam Integer customerId)
			throws OrderException, CartException {

		Orders addOr = orService.addOrder(customerId);

		return new ResponseEntity<Orders>(addOr, HttpStatus.OK);

	}

	@PutMapping("/orders/")
	public ResponseEntity<Orders> updateOrderController(@RequestBody Orders order) throws OrderException {

		Orders updateOr = orService.updateOrder(order);
		return new ResponseEntity<Orders>(updateOr, HttpStatus.OK);

	}

	@DeleteMapping("/orders/{orderId}")
	public ResponseEntity<String> deleteOrderController(@PathVariable("orderId") Integer orderId)
			throws OrderException {

		String deleteOr = orService.removeOrder(orderId);

		return new ResponseEntity<String>(deleteOr, HttpStatus.OK);
	}

	@GetMapping("/ordersId/{orderId}")
	public ResponseEntity<Orders> getOrderByIdController(@PathVariable("orderId") Integer orderId)
			throws OrderException {

		Orders getOr = orService.viewOrderById(orderId);

		return new ResponseEntity<Orders>(getOr, HttpStatus.OK);
	}

}

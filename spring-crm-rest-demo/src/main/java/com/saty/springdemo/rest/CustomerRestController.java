package com.saty.springdemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saty.springdemo.entity.Customer;
import com.saty.springdemo.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerRestController {
	
	//autowire the customer service
	
	@Autowired
	private CustomerService customerService;
	
	//add mapping for GET /customers
	
	@GetMapping("/customers")
	public List<Customer> getCustomers(){
		
		return customerService.getCustomers();
	}
	
	@GetMapping("/customers/{customerId}")
	public Customer getCustomer(@PathVariable int customerId) {	
		Customer theCustomer =  customerService.getCustomer(customerId);
		if(theCustomer == null)
			throw new CustomerNotFoundException("Customer with id "+customerId+" not found");
		return theCustomer;
	}
	
	//add a mapping for POST /customers - add new customers
	
	@PostMapping("/customers")
	public Customer addCustomer(@RequestBody Customer customer) {
		customer.setId(0);
		customerService.saveCustomer(customer);
		return customer;
	}
	
	//add a mapping for PUT /customers - update an existing customer
	@PutMapping("/customers")
	public Customer updateCustomer(@RequestBody Customer customer) {
		
		customerService.saveCustomer(customer);
		
		return customer;
	}
	
	//add a mapping for DELETE /customers/{customerId} - Delete customer with id
	
	@DeleteMapping("/customers/{customerId}")
	public String deleteCustomer(@PathVariable int customerId) {
		
		Customer theCustomer = customerService.getCustomer(customerId);
		
		//throw Exception if null
		
		if(theCustomer == null)
			throw new CustomerNotFoundException("customer with id -"+customerId+" not found");
		
		customerService.deleteCustomer(customerId);
		return "Customer with Id- "+customerId+" deleted";
	}
}

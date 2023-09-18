package com.example.demo.entities.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Customer;
import com.example.demo.repository.CustomerRepository;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class CustomerController {

	private CustomerRepository customerRepository;
	
	@GetMapping("customer")
	public List<Customer> GetAll(){
		return customerRepository.findAll();
	}
	
	@GetMapping(path = "customer/{id}")
	public Customer GetById(@PathVariable Long id) {
		return customerRepository.findById(id).get();
	}
	
	@PostMapping("customer")
	public Customer Save(@RequestBody Customer customer) {
		customerRepository.save(customer);
		return customer;
	}
	
	
}

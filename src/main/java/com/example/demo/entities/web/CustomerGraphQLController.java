package com.example.demo.entities.web;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CustomerRequest;
import com.example.demo.entities.Customer;
import com.example.demo.mapper.CustomerMapper;
import com.example.demo.repository.CustomerRepository;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class CustomerGraphQLController {
	
	private CustomerRepository customerRepository;
	private CustomerMapper customerMapper;
	@QueryMapping
	public List<Customer> allCustomers(){
		return customerRepository.findAll();
	}
	
	@QueryMapping
	public Customer customerById(@Argument Long id){
		Customer customer= customerRepository.findById(id).orElse(null);
		 if(customer==null) throw new RuntimeException(String.format("Customer %s not found ", id));
		 return customer;
	}
	
	@MutationMapping
	public Customer saveCustomer(@Argument CustomerRequest customer) {
		Customer c =customerMapper.from(customer);
		return customerRepository.save(c);
	}
	
	
}

package com.example.demo.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.example.demo.dto.CustomerRequest;
import com.example.demo.entities.Customer;

@Component
public class CustomerMapper {
	private ModelMapper modelMapper=new  ModelMapper();
	public Customer from(CustomerRequest customerRequest) {
		Customer customer=new Customer();
		modelMapper.map(customerRequest, customer);
	//	customer.setEmail(customerRequest.email());
	//	customer.setName(customerRequest.name());
		
		return customer;
	}
}

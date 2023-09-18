package com.example.demo.entities.web;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.dto.CustomerRequest;
import com.example.demo.entities.Customer;
import com.example.demo.mapper.CustomerMapper;
import com.example.demo.repository.CustomerRepository;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
@WebService(serviceName = "CustomerWS")
public class CustomerSoapService {	
	private CustomerRepository customerRepository;
	private CustomerMapper customerMapper;
	@WebMethod
 	public List<Customer> customerList(){
 		return customerRepository.findAll();
 	}
	
	@WebMethod
	public Customer customerById(@WebParam(name = "id") Long id) {
		return customerRepository.findById(id).get();
	}
	
	@WebMethod
	public Customer SaveCustomer(@WebParam(name ="customer" ) CustomerRequest customerRequest) {
		Customer customer=customerMapper.from(customerRequest);
		return customerRepository.save(customer);
	}
 	
 	
}

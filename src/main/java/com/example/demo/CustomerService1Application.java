package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.entities.Customer;
import com.example.demo.repository.CustomerRepository;

@SpringBootApplication
public class CustomerService1Application {

	public static void main(String[] args) {
		SpringApplication.run(CustomerService1Application.class, args);
	}
	
	@Bean
	CommandLineRunner start(CustomerRepository customerRepository) {
		return args ->{
			customerRepository.save(new Customer(null, "Ayoub", "Ayoub@gmail.com"));
			customerRepository.save(new Customer(null, "Najoua", "Najoua@gmail.com"));
			customerRepository.save(new Customer(null, "Maroua", "Maroua@gmail.com"));

		};
	}

}

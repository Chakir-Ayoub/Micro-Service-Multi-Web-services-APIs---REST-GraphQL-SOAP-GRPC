package com.example.demo.config;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.entities.web.CustomerSoapService;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class CxfConfig {
	private Bus bus;
	private CustomerSoapService customerSoapService;
	
	@Bean
	public EndpointImpl endpoint() {
		EndpointImpl endpoint=new EndpointImpl(bus, customerSoapService);
		endpoint.publish("/ ");
		return endpoint;
	}
	
}
